package kr.idu.OInjo_Shop.service.Member;

import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.dto.Page.PageRequestDTO;
import kr.idu.OInjo_Shop.dto.Page.PageResultDTO;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;

import java.util.List;

public interface MemberService {

    void save(MemberDTO memberDTO);
    MemberDTO login(MemberDTO memberDTO);
    PageResultDTO<MemberDTO, MemberEntity> getAllList(PageRequestDTO requestDTO);
    List<MemberDTO> findAll();
    MemberDTO findById(Long id);
    MemberDTO findEmail(String myEmail);
    void update(MemberDTO memberDTO);
    void deleteById(Long id);
    MemberDTO findByMember(String name, String phone);
    boolean findByMemberEmail(String email);
    String temporaryPassword(String email);
}
