package com.jianma.xtdm.service;

import com.jianma.xtdm.model.Permission;

public interface PermissionsService {

	public int createPermission(Permission permission);

	public int deletePermission(Long permissionId);
}
