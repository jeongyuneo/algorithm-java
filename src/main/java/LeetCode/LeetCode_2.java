package LeetCode;

public class LeetCode_2 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        String result = "";
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val;
            if (l1.next != null) {
                l1.next.val += sum / 10;
            } else if (l2.next != null) {
                l2.next.val += sum / 10;
            } else if (sum / 10 > 0) {
                l1.next = new ListNode(sum / 10);
            }
            result += sum % 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            result += l1.val % 10;
            if (l1.next != null) {
                l1.next.val += l1.val / 10;
            } else if (l1.val / 10 > 0) {
                result += l1.val / 10;
            }
            l1 = l1.next;
        }
        while (l2 != null) {
            result += l2.val % 10;
            if (l2.next != null) {
                l2.next.val += l2.val / 10;
            } else if (l2.val / 10 > 0) {
                result += l2.val / 10;
            }
            l2 = l2.next;
        }
        return toListNode(result);
    }

    private ListNode toListNode(String num) {
        ListNode previous = new ListNode(toInteger(num.charAt(num.length() - 1)));
        for (int idx = num.length() - 2; idx >= 0; idx--) {
            ListNode current = new ListNode(toInteger(num.charAt(idx)), previous);
            previous = current;
        }
        return previous;
    }

    private int toInteger(char character) {
        return character - '0';
    }
}
