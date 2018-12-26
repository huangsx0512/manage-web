package com.zimmur.platform.manage.web.service;

import java.util.Set;

public interface IAccountRoleService {

	Set<String> findRoleByAccountId(Integer accountId);
}
