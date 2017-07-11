package com.jianma.xtdm.service;

import com.jianma.xtdm.model.Role;

public interface RoleService {

	public void createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId, Long... permissionIds);
    public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
