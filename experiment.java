import java.util.Arrays;
import java.util.Random;

public class experiment {
    
    public static void title(int i){
        if (i == 1){
            System.out.println("############################");
            System.out.println("####### Experiment 1 #######");
            System.out.println("############################");
        }
        else if (i == 2) {
            System.out.println("############################");
            System.out.println("####### Experiment 2 #######");
            System.out.println("############################");
        }
        else if (i == 3) {
            System.out.println("############################");
            System.out.println("####### Experiment 3 #######");
            System.out.println("############################");
        }
    }


    public static int totalOperationCost(Heap heap){
        return heap.totalLinks() + heap.totalCuts() + heap.totalHeapifyCosts();
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


    public static int[] exp1(int[] permArr, boolean lazyMelds, Boolean lazyDecreaseKeys, Boolean verbos){

        long startTime = System.nanoTime(); // Starting of the experiment

        Heap.HeapItem[] pointers = new Heap.HeapItem[permArr.length + 1];
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        int BeforeOperation = 0;
        int AfterOperation = 0;
        int maxCost = 0;
        int currCost = 0;

        // Inserting the random array to the heap
        for (int i : permArr) {
            BeforeOperation = totalOperationCost(heap);
            pointers[i] = heap.insert(i, "a");
            AfterOperation = totalOperationCost(heap);
            currCost = AfterOperation - BeforeOperation;
            if (maxCost < currCost){
                maxCost = currCost;
            }
        }

        // deleting the minimum
        BeforeOperation = totalOperationCost(heap);
        heap.deleteMin();
        AfterOperation = totalOperationCost(heap);
        currCost = AfterOperation - BeforeOperation;
        if (maxCost < currCost) {
            maxCost = currCost;
        }

        long endTime = System.nanoTime(); // Ending of the experiment

        // Collecting and showing results
        int durationMs = (int) Math.floor((endTime - startTime) / 1_000_000.0);

        if (verbos){
            System.out.println("Time: " + String.valueOf(durationMs));
            System.out.println("Size of the Ending Heap: " + String.valueOf(heap.size()));
            System.out.println("Number of Roots: " + String.valueOf(heap.numTrees()));
            System.out.println("Number of Links: " + String.valueOf(heap.totalLinks()));
            System.out.println("Number of Cuts: " + String.valueOf(heap.totalCuts()));
            System.out.println("Total cost of Heapify: " + String.valueOf(heap.totalHeapifyCosts()));
            System.out.println("MaxOpeartion Cost: " + String.valueOf(maxCost));
        }

        return new int[]{durationMs, heap.size(), heap.numTrees(), heap.totalLinks(), 
                heap.totalCuts(), heap.totalHeapifyCosts(), maxCost
        };

    }


    public static int[] exp2(int[] permArr, boolean lazyMelds, Boolean lazyDecreaseKeys, Boolean verbos) {
        long startTime = System.nanoTime(); // Starting of the experiment

        Heap.HeapItem[] pointers = new Heap.HeapItem[permArr.length + 1];
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        int BeforeOperation = 0;
        int AfterOperation = 0;
        int maxCost = 0;
        int currCost = 0;

        // Inserting the random array to the heap
        for (int i : permArr) {
            BeforeOperation = totalOperationCost(heap);
            pointers[i] = heap.insert(i, "a");
            AfterOperation = totalOperationCost(heap);
            currCost = AfterOperation - BeforeOperation;
            if (maxCost < currCost) {
                maxCost = currCost;
            }
        }

        // deleting the minimum
        BeforeOperation = totalOperationCost(heap);
        heap.deleteMin();
        AfterOperation = totalOperationCost(heap);
        currCost = AfterOperation - BeforeOperation;
        if (maxCost < currCost) {
            maxCost = currCost;
        }

        // Delete max nodes from the Heap until 46 left
        int last = pointers.length-1;
        while (heap.size() > 46){
            BeforeOperation = totalOperationCost(heap);
            heap.delete(pointers[last]);
            AfterOperation = totalOperationCost(heap);
            currCost = AfterOperation - BeforeOperation;
            if (maxCost < currCost) {
                maxCost = currCost;
            }
            last--;
        }

        long endTime = System.nanoTime(); // Ending of the experiment

        // Collecting and showing results
        int durationMs = (int) Math.floor((endTime - startTime) / 1_000_000.0);

        if (verbos){
            System.out.println("Time: " + String.valueOf(durationMs));
            System.out.println("Size of the Ending Heap: " + String.valueOf(heap.size()));
            System.out.println("Number of Roots: " + String.valueOf(heap.numTrees()));
            System.out.println("Number of Links: " + String.valueOf(heap.totalLinks()));
            System.out.println("Number of Cuts: " + String.valueOf(heap.totalCuts()));
            System.out.println("Total cost of Heapify: " + String.valueOf(heap.totalHeapifyCosts()));
            System.out.println("MaxOpeartion Cost: " + String.valueOf(maxCost));
        }

        return new int[] { durationMs, heap.size, heap.numTrees(), heap.totalLinks(),
                heap.totalCuts(), heap.totalHeapifyCosts(), maxCost
        };

}


    public static int[] exp3(int[] permArr, boolean lazyMelds, Boolean lazyDecreaseKeys, Boolean verbos) {
        long startTime = System.nanoTime(); // Starting of the experiment

        Heap.HeapItem[] pointers = new Heap.HeapItem[permArr.length + 1];
        Heap heap = new Heap(lazyMelds, lazyDecreaseKeys);

        int BeforeOperation = 0;
        int AfterOperation = 0;
        int maxCost = 0;
        int currCost = 0;

        // Inserting the random array to the heap
        for (int i : permArr) {
            BeforeOperation = totalOperationCost(heap);
            pointers[i] = heap.insert(i, "a");
            AfterOperation = totalOperationCost(heap);
            currCost = AfterOperation - BeforeOperation;
            if (maxCost < currCost) {
                maxCost = currCost;
            }
        }

        // deleting the minimum
        BeforeOperation = totalOperationCost(heap);
        heap.deleteMin();
        AfterOperation = totalOperationCost(heap);
        currCost = AfterOperation - BeforeOperation;
        if (maxCost < currCost) {
            maxCost = currCost;
        }

        // Decrease 10% of max keys to 0
        int n = (int) Math.ceil(464646);
        int last = pointers.length - 1;
        for (int i = 0; i < n/10; i++) {
            BeforeOperation = totalOperationCost(heap);
            heap.decreaseKey(pointers[last], last);
            AfterOperation = totalOperationCost(heap);
            currCost = AfterOperation - BeforeOperation;
            if (maxCost < currCost) {
                maxCost = currCost;
            }
            last--;
        }

        // deleting the minimum
        BeforeOperation = totalOperationCost(heap);
        heap.deleteMin();
        AfterOperation = totalOperationCost(heap);
        currCost = AfterOperation - BeforeOperation;
        if (maxCost < currCost) {
            maxCost = currCost;
        }

        long endTime = System.nanoTime(); // Ending of the experiment

        // Collecting and showing results
        int durationMs = (int) Math.floor((endTime - startTime) / 1_000_000.0);

        if (verbos){
            System.out.println("Time: " + String.valueOf(durationMs));
            System.out.println("Size of the Ending Heap: " + String.valueOf(heap.size()));
            System.out.println("Number of Roots: " + String.valueOf(heap.numTrees()));
            System.out.println("Number of Links: " + String.valueOf(heap.totalLinks()));
            System.out.println("Number of Cuts: " + String.valueOf(heap.totalCuts()));
            System.out.println("Total cost of Heapify: " + String.valueOf(heap.totalHeapifyCosts()));
            System.out.println("MaxOpeartion Cost: " + String.valueOf(maxCost));
        }


        return new int[] { durationMs, heap.size, heap.numTrees(), heap.totalLinks(),
                heap.totalCuts(), heap.totalHeapifyCosts(), maxCost
        };

    }


    public static void ExpAvgResults(int exp, boolean lazyMelds, Boolean lazyDecreaseKeys) {
        int[] timeArr = new int[20];
        int[] sizeArr = new int[20];
        int[] numOfRootsArr = new int[20];
        int[] linksArr = new int[20];
        int[] cutsArr = new int[20];
        int[] heapifyCostArr = new int[20];
        int[] maxOperationArr = new int[20];

        int[] results = new int[7];
        int n = 464646;
        int[] permArr = new int[n];

        for (int i = 0; i < 20; i++) {

            permArr = generateRandomPermutation(n);

            if (exp == 1){
                results = exp1(permArr, lazyMelds, lazyDecreaseKeys, false);
                
            }
            else if (exp == 2) {
                results = exp2(permArr, lazyMelds, lazyDecreaseKeys, false);
            }
            else{
                results = exp3(permArr, lazyMelds, lazyDecreaseKeys, false);
            }

            timeArr[i] = results[0];
            sizeArr[i] = results[1];
            numOfRootsArr[i] = results[2];
            linksArr[i] = results[3];
            cutsArr[i] = results[4];
            heapifyCostArr[i] = results[5];
            maxOperationArr[i] = results[6];

        }

        double avgTime = Arrays.stream(timeArr).average().orElse(0.0);
        double avgSize = Arrays.stream(sizeArr).average().orElse(0.0);
        double avgNumOfRoots = Arrays.stream(numOfRootsArr).average().orElse(0.0);
        double avgLinks = Arrays.stream(linksArr).average().orElse(0.0);
        double avgCuts = Arrays.stream(cutsArr).average().orElse(0.0);
        double avgHeapifyCost = Arrays.stream(heapifyCostArr).average().orElse(0.0);
        double avgMaxOperation = Arrays.stream(maxOperationArr).average().orElse(0.0);
        // System.out.println();
        // System.out.println("############################");
        // System.out.println("##### AVERAGE RESULTS ######");
        // System.out.println("############################");
        System.out.println("Avg Time: " + String.valueOf(avgTime));
        System.out.println("Avg Size of the Ending Heap: " + String.valueOf(avgSize));
        System.out.println("Avg Number of Roots: " + String.valueOf(avgNumOfRoots));
        System.out.println("Avg Number of Links: " + String.valueOf(avgLinks));
        System.out.println("Avg Number of Cuts: " + String.valueOf(avgCuts));
        System.out.println("Avg Total cost of Heapify: " + String.valueOf(avgHeapifyCost));
        System.out.println("Avg MaxOpeartion Cost: " + String.valueOf(avgMaxOperation));
    }


    public static void expResultPackage(int i){

        String title1 = "==== ערמה בינומית ====";
        title1 = new StringBuilder(title1).reverse().toString();

        String title2 = "==== ערמה בינומית עצלה ====";
        title2 = new StringBuilder(title2).reverse().toString();

        String title3 = "==== ערימת פיבונאצ'י ====";
        title3 = new StringBuilder(title3).reverse().toString();

        String title4 = "==== ערימה בינומית עם ניתוקים ====";
        title4 = new StringBuilder(title4).reverse().toString();

        System.out.println("");
        System.out.println(title1);
        ExpAvgResults(i, false, false);

        System.out.println("");
        System.out.println(title2);
        ExpAvgResults(i, true, false);

        System.out.println("");
        System.out.println(title3);
        ExpAvgResults(i, true, true);

        System.out.println("");
        System.out.println(title4);
        ExpAvgResults(i, false, true);
    }


    public static void AllResults(){
        // Experiment 1
        title(1);
        expResultPackage(1);
        System.out.println("");

        // Experiment 2
        title(2);
        expResultPackage(2);
        System.out.println("");

        // Experiment 3
        title(3);
        expResultPackage(3);
    }






    public static void main(String[] args){
        //ExpAvgResults(1, true, true);
        AllResults();
    }

}

