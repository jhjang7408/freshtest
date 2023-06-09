package com.multicampus.hhh.mapper;

import com.multicampus.hhh.domain.BikeBoardVO;
import com.multicampus.hhh.domain.MemberRole;
import com.multicampus.hhh.domain.MemberVO;
import com.multicampus.hhh.dto.BasketDTO;
import com.multicampus.hhh.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MemberMapper {

    void save(MemberVO memberVO);   //회원등록
    void saveRole(MemberVO memberVO);
    MemberVO findUser(String user_id);    //회원조회
    void updateUser(MemberVO memberVO);
    void updateUserPass(MemberVO memberVO);

    MemberVO findjoinUser(String user_id);
    MemberDTO findtest(String user_id);
    MemberRole roleFind(String user_id);

    void deleteuser(String user_id);
    void deleterole(String user_id);
    MemberVO findPass(String userid);
    MemberVO memberEmailcheck(String user_id, String email);
    void socialInsert(MemberVO memberVO);
    int findRole(String userid);
    List<BasketDTO> shopCart(String userid);
    void addCart(BasketDTO basketDTO);
    void deleteCart(int bagid);
    int modifyCart(BasketDTO basketDTO);
    BasketDTO checkCart(BasketDTO basketDTO);
    int updateCartCount(int id, int count);


    List<BikeBoardVO> findbike(String userid);

}
