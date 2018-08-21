package com.xpto.toggle.Helper;

import com.xpto.toggle.Exceptions.BedRequestExcpetion;
import com.xpto.toggle.Exceptions.Error;
import com.xpto.toggle.dto.ServiceToggleDTO;

public class ToggleValidation {

    public static void serviceToggleDTOValidate(ServiceToggleDTO request) {
        if (request == null || request.getServiceName() == null || request.getVersion() == null || request.getToggle() == null || request.getToggle().getName() == null) {
            throw new BedRequestExcpetion(new Error(1, "BED-REQEST", "missing mandatory parameters"));
        } else {
            if (request.getServiceName().length() < 1 || request.getVersion().length() < 1 || request.getToggle().getName().length() < 1) {
                throw new BedRequestExcpetion(new Error(1, "BED-REQEST", "empty staring not allow"));
            }
        }

    }

    public static void getServiceToggleDTOValidate(String serviceName, String version) {
        if (serviceName == null || version == null) {
            throw new BedRequestExcpetion(new Error(1, "BED-REQEST", "missing mandatory parameters"));
        } else {
            if (serviceName.length() < 1 || version.length() < 1) {
                throw new BedRequestExcpetion(new Error(1, "BED-REQEST", "empty staring not allow"));
            }
        }

    }
}
