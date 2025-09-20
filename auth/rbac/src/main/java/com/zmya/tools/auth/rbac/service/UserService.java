package com.zmya.tools.auth.rbac.service;

import com.zmya.tools.auth.rbac.enums.UserStatusEnum;
import com.zmya.tools.auth.rbac.error.BusinessException;
import com.zmya.tools.auth.rbac.error.ErrorCodeEnum;
import com.zmya.tools.auth.rbac.model.dto.PageResultDTO;
import com.zmya.tools.auth.rbac.model.dto.UserDTO;
import com.zmya.tools.auth.rbac.model.request.ModifyUserRequest;
import com.zmya.tools.auth.rbac.model.request.PageUserRequest;
import com.zmya.tools.auth.rbac.model.request.SaveUserRequest;
import com.zmya.tools.auth.rbac.model.request.SignupRequest;
import com.zmya.tools.data.core.common.query.PageQuery;
import com.zmya.tools.data.core.common.result.PageResult;
import com.zmya.tools.data.core.dao.SysUserDao;
import com.zmya.tools.data.core.model.SysUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
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

    private final SysUserDao sysUserDao;
    private final PasswordEncoder encoder;

    public boolean signup(SignupRequest request) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(request.getUsername());
        sysUser.setPassword(encoder.encode(request.getPassword()));
        sysUser.setStatus(UserStatusEnum.NORMAL.getStatus());
        SysUser saved = sysUserDao.save(sysUser);
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
        return sysUserDao.save(sysUser);
    }

    public boolean remove(List<Long> userIds) {
        sysUserDao.removeByIdIn(userIds);
        return true;
    }

    public SysUser modify(ModifyUserRequest request) {
        Optional<SysUser> userOptional = sysUserDao.findById(request.getUserId());
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
        return sysUserDao.save(sysUser);
    }

    public PageResultDTO<UserDTO> query(PageUserRequest request) {
        PageQuery query = PageQuery.of(request.getPageNumber(), request.getPageSize());
        PageResult<SysUser> page = sysUserDao.findAll(query,
                request.getUsername(), request.getNickname(), request.getPhone(), request.getEmail());
        PageResultDTO<UserDTO> pageResultDTO = new PageResultDTO<>();
        pageResultDTO.setPageNumber(page.getPageNumber());
        pageResultDTO.setPageSize(page.getPageSize());
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
