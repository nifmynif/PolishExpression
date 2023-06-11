package com.exprogs.polishexpression.models.stack;

import java.util.*;

public abstract class AbstructQueue<T> implements Queue {
    private final ArrayList<T> queue;

    public AbstructQueue(ArrayList<T> queue) {
        this.queue = queue;
    }

    public ArrayList<T> getQueue() {
        return queue;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return queue.contains(o);
    }

    @Override
    public Iterator iterator() {
        return queue.iterator();
    }

    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @Override
    public Object[] toArray(Object[] a) {
        return queue.toArray(a);
    }

    @Override
    public boolean add(Object o) {
        return queue.add((T)o);
    }

    @Override
    public boolean remove(Object o) {
        return queue.remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        return queue.addAll(c);
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean retainAll(Collection c) {
        return queue.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection c) {
        return queue.removeAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
        return queue.containsAll(c);
    }

    @Override
    public boolean offer(Object o) {
        return add(o);
    }

    @Override
    public Object remove() {
        if (size() == 0)
            return null;
        else
            return queue.remove(size() - 1);
    }

    @Override
    public Object poll() {
        String tempOperand = (String) element();
        queue.remove(queue.size() - 1);
        return tempOperand;
    }

    @Override
    public Object element() {
        if (queue.isEmpty())
            throw new NoSuchElementException("нет хватает скобок");
        return queue.get(queue.size() - 1).toString();
    }

    @Override
    public Object peek() {
        if (queue.isEmpty())
            return null;
        return queue.get(queue.size() - 1).toString();
    }
}
