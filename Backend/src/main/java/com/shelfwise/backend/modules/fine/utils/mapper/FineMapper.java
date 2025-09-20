package com.shelfwise.backend.modules.fine.utils.mapper;

import com.shelfwise.backend.modules.fine.model.Fine;
import com.shelfwise.backend.modules.fine.utils.Dto.FineDto;
import org.springframework.stereotype.Service;

@Service
public class FineMapper {

    public FineDto toFineDto(Fine fine) {

        return FineDto.builder()
                .fineId(fine.getFineId())
                .userId(fine.getUser().getUserId())
                .tId(fine.getTransaction().getTId())
                .issuedDate(fine.getIssuedDate())
                .paidDate(fine.getPaidDate())
                .isPaid(fine.isPaid())
                .amount(fine.getAmount())
                .build();
    }

}
