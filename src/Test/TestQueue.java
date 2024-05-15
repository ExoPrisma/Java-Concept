package Test;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import Data_Structure.Queue;

public class TestQueue<E> {

  @Test
  public void TestQueueConstructor(){
    Queue<Integer> q = new Queue<Integer>();
    assertTrue(q.isEmpty());
  }
  
}
