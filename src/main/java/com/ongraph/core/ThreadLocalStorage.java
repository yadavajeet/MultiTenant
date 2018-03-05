// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.ongraph.core;

public class ThreadLocalStorage {

    private static ThreadLocal<String> tenant = new ThreadLocal();

    public static void setTenantName(String tenantName) {
        /*if(tenantName == null || tenantName == ""){
            tenantName = "TenantOne";
        }*/
        tenant.set(tenantName);
    }

    public static String getTenantName() {
        return tenant.get();
    }
    public static void clear() {
        tenant.remove();
    }
}

