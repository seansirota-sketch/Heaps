import java.util.Random;
import java.util.Arrays;

public class Tester {

    public static void iterRoots(Heap h){
        if (h.min == null){
            System.out.println("Heap is Empty");
            return;
        }

        Heap.HeapNode node = h.min.node;

        do{
            System.out.printf(String.valueOf(node.item.key));

            if (node.next != h.min.node){
                System.out.printf("-->");
            }
            node = node.next;
        } while (node != h.min.node);

        System.out.println();
        }


    public static int[] generateRandomPermutation(int n) {
        int[] arr = new int[n];

        // 1. Fill the array with values 1 to n
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        // 2. Fisher-Yates Shuffle
        Random rand = new Random();
        for (int i = n - 1; i > 0; i--) {
            // Pick a random index from 0 to i
            int j = rand.nextInt(i + 1);

            // Swap arr[i] with the element at the random index
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        return arr;
    }


    public static Heap heap1(){

        Heap.HeapNode node23 = new Heap.HeapNode(23, "s");
        Heap.HeapNode node7 = new Heap.HeapNode(7, "s");
        Heap.HeapNode node21 = new Heap.HeapNode(21, "s");
        Heap.HeapNode node3 = new Heap.HeapNode(3, "s");
        Heap.HeapNode node18 = new Heap.HeapNode(18, "s");
        Heap.HeapNode node52 = new Heap.HeapNode(52, "s");
        Heap.HeapNode node38 = new Heap.HeapNode(38, "s");
        Heap.HeapNode node39 = new Heap.HeapNode(39, "s");
        Heap.HeapNode node41 = new Heap.HeapNode(41, "s");
        Heap.HeapNode node17 = new Heap.HeapNode(17, "s");
        Heap.HeapNode node30 = new Heap.HeapNode(30, "s");
        Heap.HeapNode node24 = new Heap.HeapNode(24, "s");
        Heap.HeapNode node26 = new Heap.HeapNode(26, "s");
        Heap.HeapNode node35 = new Heap.HeapNode(35, "s");
        Heap.HeapNode node46 = new Heap.HeapNode(46, "s");

        node23.child = null;
        node23.next = node7;
        node23.prev = node24;
        node23.parent = null;
        node23.rank = 0;

        node7.child = null;
        node7.next = node21;
        node7.prev = node23;
        node7.parent = null;
        node7.rank = 0;

        node21.child = null;
        node21.next = node3;
        node21.prev = node7;
        node21.parent = null;
        node21.rank = 0;

        node3.child = node18;
        node3.next = node17;
        node3.prev = node21;
        node3.parent = null;
        node3.rank = 3;

        node18.child = node39;
        node18.next = node52;
        node18.prev = node38;
        node18.parent = node3;
        node18.rank = 1;

        node52.child = null;
        node52.next = node38;
        node52.prev = node18;
        node52.parent = node3;
        node52.rank = 0;

        node38.child = node41;
        node38.next = node18;
        node38.prev = node52;
        node38.parent = node3;
        node38.rank = 1;

        node39.child = null;
        node39.next = node39;
        node39.prev = node39;
        node39.parent = node18;
        node39.rank = 0;

        node41.child = null;
        node41.next = node41;
        node41.prev = node41;
        node41.parent = node38;
        node41.rank = 0;

        node17.child = node30;
        node17.next = node24;
        node17.prev = node3;
        node17.parent = node24;
        node17.rank = 1;

        node30.child = null;
        node30.next = node30;
        node30.prev = node30;
        node30.parent = node17;
        node30.rank = 0;

        node24.child = node26;
        node24.next = node23;
        node24.prev = node17;
        node24.parent = null;
        node24.rank = 2;

        node26.child = node35;
        node26.next = node46;
        node26.prev = node46;
        node26.parent = node24;
        node26.rank = 1;

        node35.child = null;
        node35.next = node35;
        node35.prev = node35;
        node35.parent = node26;
        node35.rank = 0;

        node46.child = null;
        node46.next = node26;
        node46.prev = node26;
        node46.parent = node24;
        node46.rank = 0;

        Heap H = new Heap(true, true);
        H.min = node3.item;
        H.size = 15;

        return H;
    }


    public static Heap heap2(Boolean lazyMelds, Boolean lazyDecreaseKeys){
        Heap.HeapNode node12 = new Heap.HeapNode(12, "12");
        Heap.HeapNode node7 = new Heap.HeapNode(7, "7");
        Heap.HeapNode node25 = new Heap.HeapNode(25, "25");
        Heap.HeapNode node15 = new Heap.HeapNode(15, "15");
        Heap.HeapNode node28 = new Heap.HeapNode(28, "28");
        Heap.HeapNode node33 = new Heap.HeapNode(33, "33");
        Heap.HeapNode node41 = new Heap.HeapNode(41, "41");

        Heap heap1 = new Heap(lazyMelds, lazyDecreaseKeys);
        heap1.min = node7.item;
        heap1.size = 7;

        node12.next = node7;
        node12.prev = node15;
        node12.rank = 0;

        node7.child = node25;
        node7.next = node15;
        node7.prev = node12;
        node7.parent = null;
        node7.rank = 1;

        node25.child = null;
        node25.next = node25;
        node25.prev = node25;
        node25.parent = node7;
        node25.rank = 0;

        node15.child = node28;
        node15.next = node12;
        node15.prev = node7;
        node15.parent = null;
        node15.rank = 2;

        node28.child = node41;
        node28.next = node33;
        node28.prev = node33;
        node28.parent = node15;
        node28.rank = 1;

        node33.child = null;
        node33.next = node28;
        node33.prev = node28;
        node33.parent = node15;
        node33.rank = 0;

        node41.child = null;
        node41.next = node41;
        node41.prev = node41;
        node41.parent = node28;
        node41.rank = 0;

        Heap H = new Heap(true, true);
        H.min = node7.item;
        H.size = 7;

        return H;
    }


    public static Heap heap3(Boolean lazyMelds, Boolean lazyDecreaseKeys) {
        Heap.HeapNode node18 = new Heap.HeapNode(18, "18");
        Heap.HeapNode node3 = new Heap.HeapNode(3, "3");
        Heap.HeapNode node37 = new Heap.HeapNode(37, "37");

        Heap.HeapNode node8 = new Heap.HeapNode(8, "8");
        Heap.HeapNode node30 = new Heap.HeapNode(30, "30");
        Heap.HeapNode node23 = new Heap.HeapNode(23, "23");
        Heap.HeapNode node22 = new Heap.HeapNode(22, "22");
        Heap.HeapNode node45 = new Heap.HeapNode(45, "45");
        Heap.HeapNode node32 = new Heap.HeapNode(32, "32");
        Heap.HeapNode node24 = new Heap.HeapNode(24, "24");
        Heap.HeapNode node55 = new Heap.HeapNode(55, "55");

        Heap.HeapNode node6 = new Heap.HeapNode(6, "6");
        Heap.HeapNode node29 = new Heap.HeapNode(29, "29");
        Heap.HeapNode node10 = new Heap.HeapNode(10, "10");
        Heap.HeapNode node44 = new Heap.HeapNode(44, "44");
        Heap.HeapNode node48 = new Heap.HeapNode(49, "48");
        Heap.HeapNode node31 = new Heap.HeapNode(31, "31");
        Heap.HeapNode node17 = new Heap.HeapNode(17, "17");
        Heap.HeapNode node50 = new Heap.HeapNode(50, "50");

        Heap heap2 = new Heap(lazyMelds, lazyDecreaseKeys);
        heap2.min = node3.item;
        heap2.size = 19;

        node18.child = null;
        node18.next = node3;
        node18.prev = null;
        node18.parent = null;
        node18.rank = 0;

        node3.child = node37;
        node3.next = node6;
        node3.prev = node18;
        node3.parent = null;
        node3.rank = 1;

        node37.child = null;
        node37.next = node37;
        node37.prev = node37;
        node37.parent = node3;
        node37.rank = 0;

        node6.child = node8;
        node6.next = node18;
        node6.prev = node3;
        node6.parent = null;
        node6.rank = 4;

        node29.child = node48;
        node29.next = node10;
        node29.prev = node8;
        node29.parent = node6;
        node29.rank = 2;

        node10.child = node17;
        node10.next = node44;
        node10.prev = node29;
        node10.parent = node6;
        node10.rank = 1;

        node44.child = null;
        node44.next = node8;
        node44.prev = node10;
        node44.parent = node6;
        node44.rank = 0;

        node48.child = node50;
        node48.next = node31;
        node48.prev = node31;
        node48.parent = node29;
        node48.rank = 1;

        node31.child = null;
        node31.next = node48;
        node31.prev = node48;
        node31.parent = node29;
        node31.rank = 0;

        node17.child = null;
        node17.next = node17;
        node17.prev = node17;
        node17.parent = node10;
        node17.rank = 0;

        node50.child = null;
        node50.next = node50;
        node50.prev = node50;
        node50.parent = node48;
        node50.rank = 0;

        //
        node8.child = node30;
        node8.next = node29;
        node8.prev = node44;
        node8.parent = node6;
        node8.rank = 3;

        node30.child = node45;
        node30.next = node23;
        node30.prev = node22;
        node30.parent = node8;
        node30.rank = 2;

        node23.child = node24;
        node23.next = node22;
        node23.prev = node30;
        node23.parent = node8;
        node23.rank = 1;

        node22.child = null;
        node22.next = node30;
        node22.prev = node23;
        node22.parent = node8;
        node22.rank = 0;

        node45.child = node50;
        node45.next = node32;
        node45.prev = node32;
        node45.parent = node30;
        node45.rank = 1;

        node32.child = null;
        node32.next = node45;
        node32.prev = node45;
        node32.parent = node30;
        node32.rank = 0;

        node24.child = null;
        node24.next = node24;
        node24.prev = node24;
        node24.parent = node23;
        node24.rank = 0;

        node55.child = null;
        node55.next = node55;
        node55.prev = node55;
        node55.parent = node45;
        node55.rank = 0;

        return heap2;
    }


    public static Heap.HeapNode node1() {
        Heap.HeapNode node1 = new Heap.HeapNode(1, "s");
        Heap.HeapNode node2 = new Heap.HeapNode(2, "s");

        node1.child = node2;
        node1.next = node1;
        node1.prev = node1;
        node1.parent = null;
        node1.rank = 1;

        node2.child = null;
        node2.next = node2;
        node2.prev = node2;
        node2.parent = node1;
        node2.rank = 0;

        return node1;
    }


    public static Heap.HeapNode node2() {
        Heap.HeapNode node1 = new Heap.HeapNode(3, "s");
        Heap.HeapNode node2 = new Heap.HeapNode(4, "s");

        node1.child = node2;
        node1.next = node1;
        node1.prev = node1;
        node1.parent = null;
        node1.rank = 1;

        node2.child = null;
        node2.next = node2;
        node2.prev = node2;
        node2.parent = node1;
        node2.rank = 0;

        return node1;
    }





    public static void main(String[] args){


        //int[] arr = generateRandomPermutation(5);
        int[] arr = new int[]{1, 2, 5, 3, 4};
        System.out.println(Arrays.toString(arr));

        Heap.HeapNode[] pointers = new Heap.HeapNode[6];

        Heap heap = new Heap(false, false);

        for (int i : arr){
            pointers[i] = heap.insert(i,"s").node;
        }
        iterRoots(heap);

        // heap.delete(pointers[10].item);
        // heap.delete(pointers[9].item);
        // heap.delete(pointers[8].item);
        // heap.delete(pointers[7].item);
        // heap.delete(pointers[6].item);
        heap.delete(pointers[5].item);
        //heap.delete(pointers[4].item);
        //heap.delete(pointers[3].item);
        //heap.delete(pointers[2].item);
        // heap.delete(pointers[1].item);
        iterRoots(heap);
        // heap.decreaseKey(pointers[5], Integer.MIN_VALUE);
        // heap.deleteMin();


        // heap.delete(pointers[4]);
        // heap.decreaseKey(pointers[4], Integer.MIN_VALUE);
        // heap.deleteMin();
        
        // heap.delete(pointers[3]);
        // heap.decreaseKey(pointers[3], Integer.MIN_VALUE);
        // heap.deleteMin();

        // heap.delete(pointers[2]);
        // heap.decreaseKey(pointers[2], Integer.MIN_VALUE);
        // heap.deleteMin();

        // heap.delete(pointers[2]);
        // heap.decreaseKey(pointers[1], Integer.MIN_VALUE);
        // heap.deleteMin();

        // iterRoots(heap);
        




        // iterRoots(heap);
        // System.out.println(heap.min.child.mark);

        
        System.out.println("");
        System.out.println("Pointers Of 1");
        System.out.println("key: " + String.valueOf(pointers[1].item.key));
        System.out.println("next: " + String.valueOf(pointers[1].next.item.key));
        System.out.println("prev: " + String.valueOf(pointers[1].prev.item.key));
        System.out.println("child: " + String.valueOf(pointers[1].child.item.key));
        System.out.println("mark: " + String.valueOf(pointers[1].mark));

        System.out.println("");
        System.out.println("Pointers Of 2");
        System.out.println("key: " + String.valueOf(pointers[2].item.key));
        System.out.println("next: " + String.valueOf(pointers[2].next.item.key));
        System.out.println("prev: " + String.valueOf(pointers[2].prev.item.key));
        System.out.println("child: " + String.valueOf(pointers[2].child.item.key));
        System.out.println("parent: " + String.valueOf(pointers[2].parent.item.key));
        System.out.println("mark: " + String.valueOf(pointers[2].mark));

        System.out.println("");
        System.out.println("Pointers Of 3");
        System.out.println("key: " + String.valueOf(pointers[3].item.key));
        System.out.println("next: " + String.valueOf(pointers[3].next.item.key));
        System.out.println("prev: " + String.valueOf(pointers[3].prev.item.key));
        //System.out.println("child: " + String.valueOf(pointers[3].child.item.key));
        System.out.println("parent: " + String.valueOf(pointers[3].parent.item.key));
        System.out.println("mark: " + String.valueOf(pointers[3].mark));

        System.out.println("");
        System.out.println("Pointers Of 4");
        System.out.println("key: " + String.valueOf(pointers[4].item.key));
        System.out.println("next: " + String.valueOf(pointers[4].next.item.key));
        System.out.println("prev: " + String.valueOf(pointers[4].prev.item.key));
        //System.out.println("child: " + String.valueOf(pointers[4].child.item.key));
        System.out.println("parent: " + String.valueOf(pointers[4].parent.item.key));

        System.out.println("");
        System.out.println("Pointers Of 5");
        System.out.println("key: " + String.valueOf(pointers[5].item.key));
        System.out.println("next: " + String.valueOf(pointers[5].next.item.key));
        System.out.println("prev: " + String.valueOf(pointers[5].prev.item.key));
        //System.out.println("child: " + String.valueOf(pointers[5].child.key));
        System.out.println("parent: " + String.valueOf(pointers[5].parent.item.key));

        // System.out.println("");
        // System.out.println("Pointers Of 6");
        // System.out.println("key: " + String.valueOf(pointers[6].key));
        // System.out.println("next: " + String.valueOf(pointers[6].next.key));
        // System.out.println("prev: " + String.valueOf(pointers[6].prev.key));
        // //System.out.println("child: " + String.valueOf(pointers[6].child.key));

        // System.out.println("");
        // System.out.println("Pointers Of 7");
        // System.out.println("key: " + String.valueOf(pointers[7].key));
        // System.out.println("next: " + String.valueOf(pointers[7].next.key));
        // System.out.println("prev: " + String.valueOf(pointers[7].prev.key));
        // System.out.println("child: " + String.valueOf(pointers[7].child.key));
        // System.out.println("parent: " + String.valueOf(pointers[7].parent.key));

        // System.out.println("");
        // System.out.println("Pointers Of 8");
        // System.out.println("key: " + String.valueOf(pointers[8].key));
        // System.out.println("next: " + String.valueOf(pointers[8].next.key));
        // System.out.println("prev: " + String.valueOf(pointers[8].prev.key));
        // //System.out.println("child: " + String.valueOf(pointers[8].child.key));
        // System.out.println("parent: " + String.valueOf(pointers[8].parent.key));

        // System.out.println("");
        // System.out.println("Pointers Of 9");
        // System.out.println("key: " + String.valueOf(pointers[9].key));
        // System.out.println("next: " + String.valueOf(pointers[9].next.key));
        // System.out.println("prev: " + String.valueOf(pointers[9].prev.key));
        // //System.out.println("child: " + String.valueOf(pointers[9].child.key));
        // System.out.println("parent: " + String.valueOf(pointers[9].parent.key));




        }

    }


