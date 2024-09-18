package org.ywk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ywk.service.DistributedLockService;

import javax.annotation.Resource;

@RestController
@RequestMapping("lock")
public class DistributedLockController {

    @Resource
    private DistributedLockService lockService;







}
