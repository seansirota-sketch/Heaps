/**
 * Heap
 *
 * An implementation of Fibonacci heap over positive integers 
 * with the possibility of not performing lazy melds and 
 * the possibility of not performing lazy decrease keys.
 *
 */
public class Heap
{
    public final boolean lazyMelds;
    public final boolean lazyDecreaseKeys;
    public HeapNode min;
    public int size;
    public int heapifyCosts;
    public int numMarkedNods;
    public int numLinks;
    public int numCuts;
    
    /**
     *
     * Constructor to initialize an empty heap.
     *
     */
    public Heap(boolean lazyMelds, boolean lazyDecreaseKeys)
    {
        this.lazyMelds = lazyMelds;
        this.lazyDecreaseKeys = lazyDecreaseKeys;
        
        // init add to Null
        this.min = null;
        this.size = 0;
        this.heapifyCosts = 0;
        this.numMarkedNods = 0;
        this.numCuts = 0;
    }


    /**
     * 
     * pre: key > 0
     *
     * Insert (key,info) into the heap and return the newly generated HeapNode.
     *
     */
    public HeapNode insert(int key, String info) 
    {   
        Heap H = new Heap(this.lazyMelds, this.lazyDecreaseKeys);
        HeapNode x = new HeapNode(key, info);
        H.min = x;
        H.size = 1;
        x.next = x.prev = x;
        this.meld(H);
        return x;
    }


    /**
     * 
     * Return the minimal HeapNode, null if empty.
     *
     */
    public HeapNode findMin()
    {
        return min; // should be replaced by student code
    }


    /**
     * 
     * Delete the minimal item.
     *
     */
    public void deleteMin()
    {

        HeapNode z = this.min;
        if (z != null) {
            // Make Heap of Min child list
            Heap childHeap = new Heap(this.lazyMelds, this.lazyDecreaseKeys);
            HeapNode y = z.child;
            for (int i = 0; i < z.rank; i++) {
                y.parent = null;
                y = y.next;
            }
            z.child = null;
            childHeap.min = y; // doesn't matter

            // Remove Min from the list
            if (z == z.next) {
                this.min = childHeap.min;
                this.consolidate();
            } else {
                z.prev.next = z.next;
                z.next.prev = z.prev;
                this.min = z.next;
                // In delete min we are always make consolidate
                this.concat(childHeap);
                this.consolidate();
            }

            this.size -= 1;
            // Update Min occurs in the consolidate logic

        }
    }


    /**
     * 
     * pre: 0<=diff<=x.key
     * 
     * Decrease the key of x by diff and fix the heap.
     * 
     */
    public void decreaseKey(HeapNode x, int diff) 
    {   
        if (this.lazyDecreaseKeys){
            x.key -= diff;
            HeapNode y = x.parent;
            if (y != null && x.key < y.key){
                this.cut(x, y);
                this.cascadingCut(y);
            }
            if (x.key < this.min.key){
                this.min = x;
            }
        }
        else{
            x.key -= diff;
            // Update min
            if (x.key < this.min.key) {
                this.min = x;
            }

            this.heapifyCosts += heapifyUp(x);
        }
    }


    /**
     * 
     * Delete the x from the heap.
     *
     */
    public void delete(HeapNode x) 
    {    
        this.decreaseKey(x, Integer.MIN_VALUE);
        this.deleteMin();
    }


    /**
     * 
     * Meld the heap with heap2
     * pre: heap2.lazyMelds = this.lazyMelds AND heap2.lazyDecreaseKeys =
     * this.lazyDecreaseKeys
     *
     */
    public void meld(Heap heap2) {
        // minimum + size fields updateted through the concat & consolidate methods
        this.concat(heap2);
        if (!this.lazyMelds){
            this.consolidate();
        }
    }

   
    /**
     * 
     * Return the number of elements in the heap
     *   
     */
    public int size()
    {
        return this.size; // should be replaced by student code
    }


    /**
     * 
     * Return the number of trees in the heap.
     * 
     */
    public int numTrees()
    {
        if (this.min == null) {
            return 0;
        }

        Heap.HeapNode node = this.min;
        int count = 1;

        do {
            if (node.next != this.min) {
                count++;
            }
            node = node.next;
        } while (node != this.min);

        return count;
    }
    
    
    /**
     * 
     * Return the number of marked nodes in the heap.
     * 
     */
    public int numMarkedNodes()
    {
        return this.numMarkedNods; // should be replaced by student code
    }
    
    
    /**
     * 
     * Return the total number of links.
     * 
     */
    public int totalLinks()
    {
        return this.numLinks; // should be replaced by student code
    }
    
    
    /**
     * 
     * Return the total number of cuts.
     * 
     */
    public int totalCuts()
    {
        return this.numCuts; // should be replaced by student code
    }
    

    /**
     * 
     * Return the total heapify costs.
     * 
     */
    public int totalHeapifyCosts()
    {
        return this.heapifyCosts; // should be replaced by student code
    }


    public void removeMin(){
        HeapNode node = this.min;

        if (node == null){
            return;
        }

        else if (node == node.next){
            if (node.child == null){
                this.min = null;
            }
            else{
                this.min = node.child;
            }
            return;
        }

        else{
            this.min = node.next;
            HeapNode x = node.child;
            HeapNode y = node.child.prev;
            node.prev.next = x;
            x.prev = node.prev;

            node.next.prev = y;
            y.next = node.next;

            node.next = null;
            node.prev = null;
        }
    }


    public Heap removeNode(){
        HeapNode node = this.min;

        if (this.min == node.next){
            Heap H = new Heap(this.lazyMelds, this.lazyDecreaseKeys);
            H.min = node;
            this.min = null;
            return H;
        }
        else{
            this.min = node.next;

            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.next = node;
            node.prev = node;

            Heap H = new Heap(this.lazyMelds, this.lazyDecreaseKeys);
            H.min = node;
            return H;
        }
    }


    public void concat(Heap H2){
        // Update size
        this.size += H2.size;
        this.heapifyCosts += H2.heapifyCosts;
        this.numLinks += H2.numLinks;
        this.numCuts += H2.numCuts;
        this.numMarkedNods += H2.numMarkedNods;

        // concatenate the root list of H2 with the root list of H
        if (this.min == null){
            this.min = H2.min;
            return;
        }
        else if (H2.min == null){
            return;
        }
        else{
            HeapNode min1 = this.min;
            HeapNode min2 = H2.min;
            HeapNode last1 = min1.prev;
            HeapNode last2 = min2.prev;

            last1.next = min2;
            min2.prev = last1;
            last2.next = min1;
            min1.prev = last2;
        }

        // Update min
        if ((this.min == null) || (H2.min != null && H2.min.key < this.min.key)){
            this.min = H2.min;
        }
        
    }


    public void consolidate(){
        int maxD = (int) Math.floor(Math.log(this.size) / Math.log(1.618)) +1 ;
        HeapNode[] A = new HeapNode[Math.max(maxD+1,2)];
        HeapNode x;
        HeapNode y;
        HeapNode tmp;
        int d;
        
        for (int i = 0; i < A.length; i++){
            A[i] = null;
        }

        int numOfTrees = this.numTrees();

        for (int i = 0; i < numOfTrees; i++){
            x = this.removeNode().min;
            d = x.rank;
            while (A[d] != null){
                y = A[d];
                if (x.key > y.key){
                    tmp = x;
                    x = y;
                    y = tmp;
                }
                HeapNode.link(y, x);
                this.numLinks++;
                A[d] = null;
                d = d + 1;
            }
            A[d] = x;
        }
        // Now the Array is full with uniqe degrees and our Heap is Empty
        for (int i = 0; i < A.length; i++){
            if (A[i] != null){
                if (this.min == null){
                    this.min = A[i];
                }
                else{
                    if (this.min == this.min.next){
                        this.min.next = A[i];
                        this.min.prev = A[i];
                        A[i].next = this.min;
                        A[i].prev = this.min;
                    }
                    else{
                        x = this.min;
                        y = this.min.next;
                        x.next = A[i];
                        A[i].prev = x;
                        y.prev = A[i];
                        A[i].next = y;
                    }
                }
                if (A[i].key < this.min.key){
                    this.min = A[i];
                }
            }
        }
    }


    public int heapifyUp(HeapNode x){
        int count = 0;
        HeapNode y = x;
        HeapNode z = y.parent;
        HeapNode tmp;

        while (z != null && y.key < z.key) {
            count++;
            HeapNode p = z.parent;
            tmp = new HeapNode(y);
            tmp.next = y.next;
            tmp.prev = y.prev;
            tmp.parent = y.parent;

            HeapNode node;
            HeapNode[] tmp_childs = new HeapNode[tmp.rank];
            HeapNode[] z_childs = new HeapNode[z.rank];

            // make list of y "children"
            if (tmp.child != null) {
                node = tmp.child;
                for(int i = 0; i < tmp_childs.length; i++ ){
                    tmp_childs[i] = node;
                    node = node.next;
                }
            }

            // make list of z children
            if (z.child != null) {
                node = z.child;
                for (int i = 0; i < z_childs.length; i++) {
                    z_childs[i] = node;
                    node = node.next;
                }
            }



            if (p != null && p.child == z) {
                p.child = y;
            }

            if (z.child == y) {
                y.child = z;
            } else {
                y.child = z.child;
            }

            for (HeapNode a : z_childs){
                a.parent = y;
            }

            if (z.next != z) {
                y.next = z.next;
                y.prev = z.prev;
                z.prev.next = y;
                z.next.prev = y;
            } else {
                y.next = y;
                y.prev = y;
            }

            y.parent = z.parent;
            y.rank = z.rank;

            z.child = tmp.child;
            z.parent = y;

            for (HeapNode b : tmp_childs) {
                b.parent = z;
            }

            if (tmp.next != y) {
                z.next = tmp.next;
                z.prev = tmp.prev;
                tmp.prev.next = z;
                tmp.next.prev = z;
            } else {
                z.prev = z;
                z.next = z;
            }

            z.rank = tmp.rank;
            z = p;
        }
        return count;
    }


    public void cut(HeapNode x, HeapNode y){
        if (y.rank == 1){
            y.child = null;
        }
        else if (y.child == x){
            y.child = x.next;
            x.next.prev = x.prev;
            x.prev.next = x.next;
        }
        else{
            x.next.prev = x.prev;
            x.prev.next = x.next; 
        }
        y.rank -= 1;
        x.parent = null;
        x.next = x;
        x.prev = x;
        x.mark = false;
        this.numMarkedNods--;
        Heap singlton = new Heap(this.lazyMelds, this.lazyDecreaseKeys);
        singlton.min = x;
        this.meld(singlton);
        this.numCuts++;
    }


    public void cascadingCut(HeapNode y){
        HeapNode z = y.parent;
        if (z != null){
            if (y.mark == false){
                y.mark = true;
                this.numMarkedNods++;
            }
            else{
                this.cut(y,z);
                cascadingCut(z);
            }
        }
    }

    /**
     * Class implementing a node in a ExtendedFibonacci Heap.
     *  
     */
    public static class HeapNode{
        public int key;
        public String info;
        public HeapNode child;
        public HeapNode next;
        public HeapNode prev;
        public HeapNode parent;
        public int rank;
        public boolean mark;
    
        public HeapNode(int key, String info){ // deep clone
            this.key = key;
            this.info = info;
            this.child = null;
            this.next = null;
            this.prev = null;
            this.parent = null;
            this.rank = 0;
            this.mark = false;
        }

        public HeapNode(HeapNode other) { // deep clone
            this.key = other.key;
            this.info = other.info;
            this.child = other.child;
            this.next = null;
            this.prev = null;
            this.parent = null;
            this.rank = other.rank;
            this.mark = other.mark;
        }

        public static void link(HeapNode node1, HeapNode newRoot)
        {
            if (newRoot.child == null){
                newRoot.child = node1;
                node1.parent = newRoot;

                node1.next = node1;
                node1.prev = node1;
            }
            else{
                HeapNode last = newRoot.child.prev;
                node1.parent = newRoot;
                node1.next = newRoot.child;
                newRoot.child.prev = node1;
                newRoot.child = node1;
                last.next = node1;
                node1.prev = last;
            }
            newRoot.mark = false;
            newRoot.rank += 1;
        }
    }


}

