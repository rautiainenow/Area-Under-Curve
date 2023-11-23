
public class PriorityQueue {
	private Interval [] heap; // An array that encodes a max-heap data structure.
	private int size;	// The size of allocated buffer for the heap.
	private int numElements;	// The number of elements currently stored. 

	/**
		Constructor: s is the initial size of the heap.
	*/
	public PriorityQueue(int s) {
		size = s;
		heap = new Interval[size + 1];	// 1 extra element allows us to use 1-based indexing. The max heap stores intervals keyed on their lengths.
		numElements = 1;
	}

	/**
		Inserts a new Interval k into the heap. Automatically expands the heap if the buffer allocated is full.
	*/
	public void insert(Interval k) {

		if (numElements == size) {
			size = size*2;
			Interval[] temp = new Interval[size];
	
			for (int i = 1; i < numElements; i++)
			{
				temp[i]= heap[i];
				siftUp(i);
			}
			heap = temp;
		}	

		heap[numElements] = k; 
		siftUp(numElements);
		numElements++;
	}

	/**
		Returns the maximum Interval from the heap (usually the one with the largest length. See the compareTo function of Interval for more details on the comparison.
	*/
	public Interval remove_max() {
		
		if (numElements == 1) return null; // Retuns null if heap is empty.
		
		Interval max = heap[1];
		heap[1] = heap[numElements-1];
		numElements--;
		siftDown(1);

		return max; // Replace this statement with returning the max element (root) in the heap.
	}

	public int getParent(int i)
	{ 
		return i / 2;
	}
	public int getRchild(int i)
	{ 
		return (2*i)+1;
	}
	public int getLchild(int i)
	{ 
		return 2*i;
	}

	public void siftUp(int i) 
	{		
		if(numElements == 1) return;

		int parentIndex = getParent(i);
		if (parentIndex < 1) return;

		Interval parent = heap[parentIndex]; 

		if(parent.compareTo(heap[i]) < 0)
		{
			Interval temp = heap[i];
			heap[i] = heap[parentIndex];
			heap[parentIndex] = temp; 
			siftUp(parentIndex);
		}
	}

	public void siftDown(int i) 
	{
		if (i >= numElements)
			return;
		Interval lchild = null;
		Interval rchild = null;
		if(getLchild(i) < numElements) {
			lchild = heap[2*i];
		}
		if(getRchild(i) < numElements) {
			rchild = heap[2*i+1];
		}

		if(lchild == null && rchild == null) return;
		else if (rchild == null && heap[i].compareTo(lchild) > 0) 
		{
			Interval temp = heap[i];
			heap[i] = heap[getLchild(i)];
			heap[getLchild(i)] = temp; 
			siftDown(getLchild(i));
		}
		else if (lchild == null && heap[i].compareTo(rchild) > 0)
		{
			Interval temp = heap[i];
			heap[i] = heap[getRchild(i)];
			heap[getRchild(i)] = temp; 
			siftDown(getRchild(i));
		}
		else
		{
			//System.out.println(i + " " + getLchild(i) + " " + getRchild(i));
			if(lchild.compareTo(rchild) < 0)
			{
				if (heap[i].compareTo(rchild) < 0)
				{
					Interval temp = heap[i];
					heap[i] = heap[getRchild(i)];
					heap[getRchild(i)] = temp; 
					siftDown(getRchild(i));
				}

			}
			else 
			{
				if(heap[i].compareTo(lchild) < 0)
				{
					Interval temp = heap[i];
					heap[i] = heap[getLchild(i)];
					heap[getLchild(i)] = temp; 
					siftDown(getLchild(i));
				}
			}
		}

	}

	/**
		This function prints the contents of the array that encodes a heap.
	*/
	public void print() {
		System.out.println("Printing heap:");
		for (int i = 1; i < numElements; ++i)
			System.out.println(heap[i]);
	}
}
