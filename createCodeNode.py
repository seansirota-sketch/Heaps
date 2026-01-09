def create(arr):
    for i in arr:
        print('Heap.HeapNode node' + str(i) + ' = new Heap.HeapNode(' + str(i) + ',"s");')
        
    for i in arr:
        print()
        print("node" + str(i) + ".child = node;")
        print("node" + str(i) + ".next = node;")
        print("node" + str(i) + ".prev = node;")
        print("node" + str(i) + ".parent = node;")
        print("node" + str(i) + ".rank= node;")  
        
arr = [23, 7, 21, 3, 18, 52, 38, 39, 41, 17, 30, 24, 26, 35, 46]
create(arr)
