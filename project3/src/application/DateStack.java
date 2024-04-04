package application;

import java.time.LocalDate;


public class DateStack {
	LocalDate date;
    Stack martyrs;

    public DateStack(LocalDate date) {
        this.date = date;
        this.martyrs = new Stack();
    }

    public void push(Martyr martyr) {
        martyrs.push(martyr);
    }

    public Martyr pop() {
        return (Martyr) martyrs.pop();
    }

    public boolean isEmpty() {
        return martyrs.isEmpty();
    }

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Stack getMartyrs() {
		return martyrs;
	}

	public void setMartyrs(Stack martyrs) {
		this.martyrs = martyrs;
	}

	public int getMartyrCount() {
		// TODO Auto-generated method stub
		return Martyr.martyrCount;
	}

	@Override
	public String toString() {
		return "date=" + date + "\n martyrs=" + martyrs.printStack() ;
	}}

