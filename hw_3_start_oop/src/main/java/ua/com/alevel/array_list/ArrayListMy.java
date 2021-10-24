package ua.com.alevel.array_list;


public class ArrayListMy <T> {

    int size=0;
    private int index;
    private static final int DEFAULT_CAPACITY  = 15;
    private Object elements[];
    public int size() {
        return size;
    }

    public ArrayListMy(){
        elements = new Object[DEFAULT_CAPACITY];
    }
    public void add(T elements)
    {
        if (this.index==this.elements.length) grow();
        this.elements[this.index]=elements;
        this.index++;
        this.size++;
    }
    public void remove(int i){
        if (i<0){
            System.out.println("index cannot be less 0");
            return;
        }
        else {
            System.arraycopy(elements, i + 1, elements, i, index - i);
            this.size--;
            this.index--;
        }
    }

    public void remove(Object o){
        for (int i = 0; i <size ; i++) {
            if (o.equals(elements[i])){
              System.arraycopy(elements,i+1,elements,i,size-i-1);
                this.size--;
                this.index--;
            }
        }

    }
    private void grow(){
        int newSize = (this.elements.length)+(this.elements.length/2);
        System.arraycopy(elements, 0, elements, 0, Math.min(elements.length, newSize));
        //elements = Arrays.copyOf(elements,newSize);
    }
    public T get(int index){
        if (index>=size||index<0){
            throw new IndexOutOfBoundsException("Index: "+index+", Size "+index);
        }
        return (T) elements[index];
    }

}
