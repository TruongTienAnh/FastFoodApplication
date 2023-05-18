package BUS;

import GUI.QuantityPane;

public interface QuantityEvent {
		public void  onIncrease(int row,QuantityPane qp);
		public void  onDecrease(int row,QuantityPane qp);
}
