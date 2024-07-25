package com.thesis.sofen.services;

import com.thesis.sofen.common.EUserStatus;
import com.thesis.sofen.dto.DetailDTO;
import com.thesis.sofen.entities.HotelPolicy.HotelPolicy;
import com.thesis.sofen.entities.HotelPolicy.HotelPolicyDetail;
import com.thesis.sofen.entities.Language;
import com.thesis.sofen.exception.DuplicateFieldException;
import com.thesis.sofen.exception.ResourceNotFoundException;
import com.thesis.sofen.mapper.HotelPolicyDetailMapper;
import com.thesis.sofen.mapper.HotelPolicyMapper;
import com.thesis.sofen.repositories.HotelPolicyDetailRepository;
import com.thesis.sofen.repositories.HotelPolicyRepository;
import com.thesis.sofen.repositories.LanguageRepository;
import com.thesis.sofen.request.hotelPolicy.HotelPoliciesRequest;
import com.thesis.sofen.response.HotelPolicy.HotelPolicyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelPolicyServices {
    private final HotelPolicyRepository hotelPolicyRepo;
    private final LanguageRepository languageRepo;
    private final HotelPolicyDetailRepository hotelPolicyDetailRepo;

    /**
     * Create hotel policies.
     */
    public HotelPolicy createPolicies(HotelPoliciesRequest request) throws ResourceNotFoundException, DuplicateFieldException {
        if (hotelPolicyRepo.findByName(request.getName()) != null) {
            throw new DuplicateFieldException("policy_name_duplicate", "Chính sách đã tồn tại với tên " + request.getName());
        }
        HotelPolicy hotelPolicy = new HotelPolicy();
        hotelPolicy.setName(request.getName());
        hotelPolicy.setStatus(EUserStatus.ACTIVE);
        hotelPolicyRepo.save(hotelPolicy);

        List<HotelPolicyDetail> hotelPolicyDetails = createPolicy(request.getPolicyDetails(), hotelPolicy);

        return hotelPolicy;
    }

    /**
     * Create policy details.
     * If language is not found or policy name already exists with the language
     * then delete the hotel policy created before
     */
    private List<HotelPolicyDetail> createPolicy(List<DetailDTO> request, HotelPolicy hotelPolicy)
            throws DuplicateFieldException, ResourceNotFoundException {
        for (DetailDTO policyRequest : request) {
            Language language = languageRepo.findByCode(policyRequest.getLangCode());
            //Check if language is not found
            if (language == null) {
                hotelPolicyRepo.delete(hotelPolicy);
                throw new ResourceNotFoundException("language_not_found", "Không thể tìm thấy ngôn ngữ với mã: " + policyRequest.getLangCode());
            }
            //Check if policy name already exists with the language
            if (hotelPolicyDetailRepo.findByNameAndLanguageId(policyRequest.getName(), language.getId()) != null) {
                hotelPolicyRepo.delete(hotelPolicy);
                throw new DuplicateFieldException(
                        "policy_name_detail_duplicate",
                        "Chính sách chi tiết đã tồn tại với tên: " + policyRequest.getName());
            }
        }
        List<HotelPolicyDetail> list = new ArrayList<>();

        //Create policy details
        for (DetailDTO policyRequest : request) {
            Language language = languageRepo.findByCode(policyRequest.getLangCode());
            HotelPolicyDetail hotelPolicyDetail = HotelPolicyDetailMapper.INSTANCE.toEntity(policyRequest, language, hotelPolicy);
            hotelPolicyDetailRepo.save(hotelPolicyDetail);
            list.add(hotelPolicyDetail);
        }
        return list;
    }

    public ResponseEntity<?> findPolicies() {
        List<HotelPolicy> hotelPolicies = hotelPolicyRepo.findAll();
        List<HotelPolicyResponse> response = new ArrayList<>();
        for (HotelPolicy hotelPolicy : hotelPolicies) {
            HotelPolicyResponse policy = HotelPolicyMapper.INSTANCE.toResponse(hotelPolicy);
            response.add(policy);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public Object updatePolicies(Long policyId, HotelPoliciesRequest request)
            throws ResourceNotFoundException, DuplicateFieldException {
        HotelPolicy hotelPolicy = hotelPolicyRepo.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("policy_not_found", "Không tìm thấy policy với id " + policyId));

        if (hotelPolicyRepo.existsByNameWithOtherId(policyId, request.getName())) {
            throw new DuplicateFieldException("policy_name_duplicate", "Policy.json đã tồn tại với tên: " + request.getName());
        }
        hotelPolicy.setName(request.getName());

        for (DetailDTO policyRequest : request.getPolicyDetails()) {
            Language language = languageRepo.findByCode(policyRequest.getLangCode());
            if (language == null) {
                throw new ResourceNotFoundException(
                        "language_not_found",
                        "Không tìm thấy ngôn ngữ " + policyRequest.getLangCode());
            }
            if (hotelPolicyDetailRepo.existsByNameAndLanguageIdWithOtherId(policyRequest.getName(), language.getId(), policyId)) {
                throw new DuplicateFieldException(
                        "policy_name_detail_duplicate",
                        "Chính sách chi tiết đã tồn tại tên: " + policyRequest.getName() + "với ngôn ngữ: " + language.getCode());
            }
        }

        updatePolicy(hotelPolicy.getPolicyDetails(), request);

        return "OK";
    }

    private void updatePolicy(List<HotelPolicyDetail> oldList, HotelPoliciesRequest request) {
        List<DetailDTO> newList = request.getPolicyDetails();
        oldList.forEach(oldPolicy -> {
            newList.stream()
                    .filter(newPolicy -> oldPolicy.getLanguage().getCode().equals(newPolicy.getLangCode()))
                    .findFirst()
                    .ifPresent(newPolicy -> {
                        oldPolicy.setName(newPolicy.getName());
                        oldPolicy.setDescription(newPolicy.getDescription());
                        hotelPolicyDetailRepo.save(oldPolicy);
                    });
        });
    }


    public Object updateStatus(Long policyId, EUserStatus status) throws ResourceNotFoundException {
        HotelPolicy hotelPolicy = hotelPolicyRepo.findById(policyId)
                .orElseThrow(() -> new ResourceNotFoundException("policy_not_found", "Không tìm thấy policy với id " + policyId));
        hotelPolicy.setStatus(status);
        hotelPolicyRepo.save(hotelPolicy);
        return "OK";
    }

    public List<HotelPolicy> getAllHotelPolicy(UUID hotelId) {
        if(hotelId != null)
            return hotelPolicyRepo.findAllByHotelId(hotelId);
        return hotelPolicyRepo.findAll();
    }
}
