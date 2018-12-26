package com.zimmur.platform.manage.web.service;

import java.util.Set;

public interface IAccountPermissionService {

	Set<String> findPermissionByaccountId(Integer accountId);
}
