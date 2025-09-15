import model.Transaction;
import service.AreadministrationService;

import java.util.*;

public class Main {
//    public static void main(String[] args) {
//        List<String> existingCodes = List.of("500001", "500001001", "500001001001", "500002", "500002001", "500001125", "500001001125");
//
//        // Ví dụ: Tạo mã cho cấp tỉnh mã "500"
//        String nextTinhCode = AreadministrationService.genCodeNew(existingCodes, "500");
//        System.out.println("Mã tỉnh tiếp theo: " + nextTinhCode);
//
//        // Ví dụ: Tạo mã cho cấp huyện thuộc tỉnh mã "500001"
//        String nextHuyenCode = AreadministrationService.genCodeNew(existingCodes, "500005");
//        System.out.println("Mã huyện tiếp theo: " + nextHuyenCode);
//
//        // Ví dụ: Tạo mã cho cấp xã thuộc huyện mã "500001001"
//        String nextXaCode = AreadministrationService.genCodeNew(existingCodes, "500001001");
//        System.out.println("Mã xã tiếp theo: " + nextXaCode);
//    }

    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction("U01", 120),
                new Transaction("U02", 500),
                new Transaction("U01", 300),
                new Transaction("U03", 700),
                new Transaction("U02", 200),
                new Transaction("U04", 50),
                new Transaction("U05", 700),
                new Transaction("U06", 900)
        );
        int N = 2;
        List<Map.Entry<String, Integer>> topCustomers = findTopCustomers(transactions, N);

        System.out.println("Top " + N + " customers chi tieu nhieu nhat: ");
        for (var entry : topCustomers) {
            System.out.println("User: " + entry.getKey() + ", Tong chi tieu: " + entry.getValue());
        }
    }

    public static List<Map.Entry<String, Integer>> findTopCustomers(List<Transaction> transactions, int n) {
        // B1: tính tổng chi tiêu của từng user
        Map<String, Integer> spending = new HashMap<>();
        for (Transaction t : transactions) {
            spending.put(t.getUserId(), spending.getOrDefault(t.getUserId(), 0) + t.getAmount());
        }

        // B2: đưa các entry vào PriorityQueue (min-heap) để lấy top n
        PriorityQueue<Map.Entry<String, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<String, Integer> entry : spending.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > n) {
                minHeap.poll(); // bỏ bớt phần tử nhỏ nhất
            }
        }

        // B3: lấy kết quả từ heap (đảo ngược vì min-heap)
        List<Map.Entry<String, Integer>> result = new ArrayList<>(minHeap);
        result.sort((a, b) -> b.getValue().compareTo(a.getValue())); // sắp xếp giảm dần
        return result;
    }

}