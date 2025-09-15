package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AreadministrationService {

    // Hàm để lấy mã cấp mới dựa vào mã hiện có
    public static String generateNextCode(List<String> existingCodes, String parentCode) {
        int level = parentCode.length();
        int nextCode;

        // Lấy danh sách các mã thuộc cùng cấp với mã hiện tại
        List<String> sameLevelCodes = new ArrayList<>();
        for (String code : existingCodes) {
            if (code.startsWith(parentCode) && code.length() == level + 3) {
                sameLevelCodes.add(code);
            }
        }

        // Nếu không có mã con nào thì bắt đầu từ mã đầu tiên cho cấp đó
        if (sameLevelCodes.isEmpty()) {
            nextCode = 1;
        } else {
            // Lấy mã lớn nhất trong cấp hiện tại
            List<Integer> numericCodes = new ArrayList<>();
            for (String code : sameLevelCodes) {
                numericCodes.add(Integer.parseInt(code.substring(level)));
            }
            nextCode = Collections.max(numericCodes) + 1;
        }

        // Định dạng mã mới với số ký tự phù hợp cho từng cấp
        String nextCodeStr = String.format("%03d", nextCode);
        return parentCode + nextCodeStr;
    }

    public static String genCodeNew(List<String> codes, String parentCode) {
        List<String> sameLevelCode = new ArrayList<>();
        int nextCode;
        for (String code : codes) {
            if (code.startsWith(parentCode) && code.length() == parentCode.length() + 3) {
                sameLevelCode.add(code);
            }
        }
        if (sameLevelCode.isEmpty()) {
            nextCode = 1;
        } else {
            List<Integer> numbericCode = new ArrayList<>();
            for (String code : sameLevelCode) {
                numbericCode.add(Integer.parseInt(code.substring(parentCode.length())));
            }
            nextCode = Collections.max(numbericCode) + 1;
        }
        String nextCodeStr = String.format("%03d", nextCode);
        return parentCode + nextCodeStr;
    }
}
