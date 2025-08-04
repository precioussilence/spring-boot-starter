package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.entity.SysUser;
import com.zmya.tools.auth.rbac.enums.UserStatusEnum;
import com.zmya.tools.auth.rbac.error.BusinessException;
import com.zmya.tools.auth.rbac.error.ErrorCodeEnum;
import com.zmya.tools.auth.rbac.model.dto.PageResultDTO;
import com.zmya.tools.auth.rbac.model.dto.UserDTO;
import com.zmya.tools.auth.rbac.model.request.ModifyUserRequest;
import com.zmya.tools.auth.rbac.model.request.QueryUserRequest;
import com.zmya.tools.auth.rbac.model.request.SaveUserRequest;
import com.zmya.tools.auth.rbac.model.request.SignupRequest;
import com.zmya.tools.auth.rbac.repository.SysUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final SysUserRepository sysUserRepository;
    private final PasswordEncoder encoder;

    public boolean signup(SignupRequest request) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(request.getUsername());
        sysUser.setPassword(encoder.encode(request.getPassword()));
        sysUser.setStatus(UserStatusEnum.NORMAL.getStatus());
        SysUser saved = sysUserRepository.save(sysUser);
        return Objects.nonNull(saved.getId());
    }

    public SysUser save(SaveUserRequest request) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(request.getUsername());
        sysUser.setPassword(encoder.encode(request.getPassword()));
        sysUser.setNickname(request.getNickname());
        sysUser.setEmail(request.getEmail());
        sysUser.setPhone(request.getPhone());
        sysUser.setAvatar(request.getAvatar());
        sysUser.setStatus(UserStatusEnum.NORMAL.getStatus());
        return sysUserRepository.save(sysUser);
    }

    public boolean remove(List<Long> userIds) {
        sysUserRepository.removeByIdIn(userIds);
        return true;
    }

    public SysUser modify(ModifyUserRequest request) {
        Optional<SysUser> userOptional = sysUserRepository.findById(request.getUserId());
        if (userOptional.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_FOUND);
        }
        SysUser sysUser = userOptional.get();
        if (StringUtils.hasText(request.getNickname())) {
            sysUser.setNickname(request.getNickname());
        }
        if (StringUtils.hasText(request.getEmail())) {
            sysUser.setEmail(request.getEmail());
        }
        if (StringUtils.hasText(request.getPhone())) {
            sysUser.setPhone(request.getPhone());
        }
        UserStatusEnum status = UserStatusEnum.getByStatus(request.getStatus());
        if (Objects.nonNull(status)) {
            sysUser.setStatus(status.getStatus());
        }
        if (StringUtils.hasText(request.getAvatar())) {
            sysUser.setAvatar(request.getAvatar());
        }
        return sysUserRepository.save(sysUser);
    }

    public PageResultDTO<UserDTO> query(QueryUserRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPageNumber(), request.getPageSize());
        Page<SysUser> page = sysUserRepository.findAll(pageRequest);
        PageResultDTO<UserDTO> pageResultDTO = new PageResultDTO<>();
        pageResultDTO.setPageNumber(page.getNumber());
        pageResultDTO.setPageSize(page.getSize());
        pageResultDTO.setTotalElements(page.getTotalElements());
        pageResultDTO.setTotalPages(page.getTotalPages());
        pageResultDTO.setContent(from(page.getContent()));
        return pageResultDTO;
    }

    private List<UserDTO> from(List<SysUser> sysUsers) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (SysUser sysUser : sysUsers) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(sysUser, userDTO);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

}
