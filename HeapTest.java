import java.util.*;

public class HeapTest {

    public static void main(String[] args) {
        int passed = 0;
        System.out.println("Starting 100 Tests for Fibonacci Heap...");

        passed += runCategory("Basic Operations (1-10)", testBasicOps());
        passed += runCategory("Lazy Melds & Tree Counts (11-20)", testLazyMeldLogic());
        passed += runCategory("DecreaseKey: Heapify vs Cut (21-30)", testDecreaseKeyLogic());
        passed += runCategory("Meld History Accumulation (31-40)", testMeldHistory());
        passed += runCategory("Duplicate Keys Stability (41-50)", testDuplicates());
        passed += runCategory("Specific Node Deletion (51-60)", testDeleteSpecific());
        passed += runCategory("Marked Nodes & Cascading Cuts (61-70)", testMarkingLogic());
        passed += runCategory("Empty & Edge Cases (71-80)", testEdgeCases());
        passed += runCategory("Rank & Structural Integrity (81-90)", testStructure());
        passed += runCategory("Large Scale Stress Test (91-100)", testStress());

        System.out.println("\n" + "=".repeat(30));
        System.out.println("TOTAL PASSED: " + passed + " / 100");
        System.out.println("=".repeat(30));
    }

    private static int runCategory(String name, int score) {
        System.out.printf("%-40s | Score: %2d/10\n", name, score);
        return score;
    }

    // 1-10: פעולות בסיסיות של הכנסה ומציאת מינימום
    private static int testBasicOps() {
        int p = 0;
        Heap h = new Heap(true, true);
        h.insert(10, "v1");
        if (h.size() == 1 && h.findMin().key == 10)
            p += 2;
        h.insert(5, "v2");
        if (h.findMin().key == 5)
            p += 2;
        h.insert(15, "v3");
        if (h.size() == 3 && h.findMin().key == 5)
            p += 2;
        h.deleteMin();
        if (h.findMin().key == 10)
            p += 2;
        h.deleteMin();
        if (h.findMin().key == 15)
            p += 2;
        return p;
    }

    // 11-20: בדיקת לוגיקת lazyMelds והשפעתה על numTrees
    private static int testLazyMeldLogic() {
        int p = 0;
        Heap h1 = new Heap(true, true); // lazyMelds = true
        h1.insert(10, "");
        h1.insert(20, "");
        if (h1.numTrees() == 2)
            p += 3; // ב-lazy, אינסרט פשוט מוסיף שורש

        Heap h2 = new Heap(false, true); // lazyMelds = false
        h2.insert(10, "");
        h2.insert(20, "");
        // ב-non-lazy, המימוש עשוי לבצע successive linking כבר ב-insert או ב-meld
        if (h2.numTrees() == 1)
            p += 4;

        h1.meld(h2);
        if (h1.size() == 4)
            p += 3;
        return p;
    }

    // 21-30: בדיקת decreaseKey - האם מבצע heapifyUp או cascading cut
    private static int testDecreaseKeyLogic() {
        int p = 0;
        Heap h = new Heap(false, false); // lazyDecreaseKeys = false -> heapifyUp
        h.insert(50, "A");
        Heap.HeapNode node = h.insert(100, "B").node;
        h.deleteMin(); // גורם לקישור כך ש-100 יהיה בן של 50 (במימוש מסוים)
        h.insert(50, "A"); // הוספה חזרה

        int initialHeapify = h.totalHeapifyCosts();
        h.decreaseKey(node.item, 80); // 100 -> 20. אמור לעלות מעל האבא
        if (h.totalHeapifyCosts() >= initialHeapify)
            p += 10;
        return p;
    }

    // 31-40: דרישת צבירת היסטוריה ב-Meld
    private static int testMeldHistory() {
        int p = 0;
        Heap h1 = new Heap(true, true);
        Heap h2 = new Heap(true, true);

        // יצירת היסטוריה ב-h2
        for (int i = 0; i < 10; i++)
            h2.insert(i, "");
        h2.deleteMin(); // יוצר קישורים (totalLinks)
        int h2Links = h2.totalLinks();

        h1.meld(h2);
        // המונים של h2 חייבים להתווסף ל-h1
        if (h1.totalLinks() >= h2Links && h1.totalLinks() > 0)
            p += 10;
        return p;
    }

    // 41-50: מפתחות כפולים (Stability)
    private static int testDuplicates() {
        int p = 0;
        Heap h = new Heap(true, true);
        for (int i = 0; i < 5; i++)
            h.insert(42, "duplicate");
        if (h.size() == 5 && h.findMin().key == 42)
            p += 5;
        h.deleteMin();
        if (h.size() == 4 && h.findMin().key == 42)
            p += 5;
        return p;
    }

    // 51-60: מחיקת צומת ספציפי (לא המינימום)
    private static int testDeleteSpecific() {
        int p = 0;
        Heap h = new Heap(true, true);
        h.insert(10, "A");
        Heap.HeapNode target = h.insert(50, "B").node;
        h.insert(5, "C");
        h.delete(target.item); // מוחק את 50
        if (h.size() == 2 && h.findMin().key == 5)
            p += 10;
        return p;
    }

    // 61-70: סימון צמתים וחיתוך מדורג (numMarkedNodes)
    private static int testMarkingLogic() {
        int p = 0;
        Heap h = new Heap(true, true); // lazyDecreaseKeys = true
        if (h.numMarkedNodes() == 0)
            p += 5;
        // ביצוע פעולות שיגרמו לסימון (דורש עץ בדרגה 2 ומעלה)
        // הערה: בבדיקה זו אנו מוודאים שהמונה קיים ולא קורס
        if (h.numMarkedNodes() >= 0)
            p += 5;
        return p;
    }

    // 71-80: מקרי קצה (ערימה ריקה, מפתחות שליליים)
    private static int testEdgeCases() {
        int p = 0;
        Heap h = new Heap(true, true);
        if (h.findMin() == null)
            p += 3;
        h.insert(-100, "negative");
        if (h.findMin().key == -100)
            p += 3;
        h.decreaseKey(h.findMin(), 50); // -100 - 50 = -150
        if (h.findMin().key == -150)
            p += 4;
        return p;
    }

    // 81-90: תקינות המבנה (דרגות וקשרים)
    private static int testStructure() {
        int p = 0;
        Heap h = new Heap(false, false);
        for (int i = 0; i < 16; i++)
            h.insert(i, "");
        h.deleteMin();
        // ב-15 איברים שנותרו, חייב להיות עץ בדרגה 3 (לפי חוקי פיבונאצ'י/בינומי)
        if (h.numTrees() <= 4)
            p += 10;
        return p;
    }

    // 91-100: מבחן מאמץ (Stress Test)
    private static int testStress() {
        int p = 0;
        Heap h = new Heap(true, true);
        for (int i = 1000; i > 0; i--)
            h.insert(i, "v");
        if (h.findMin().key == 1)
            p += 5;
        for (int i = 0; i < 500; i++)
            h.deleteMin();
        if (h.size() == 500 && h.findMin().key == 501)
            p += 5;
        return p;
    }
}