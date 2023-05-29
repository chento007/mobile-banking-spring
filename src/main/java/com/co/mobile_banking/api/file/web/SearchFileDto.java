package com.co.mobile_banking.api.file.web;

import java.io.File;

/**
 * use to for respone after search file in server
 * @param file respone file  after search
 * @param status respone true or false after search
 */
public record SearchFileDto(
        File file,
        boolean status
) {
}
