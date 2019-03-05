package edu.uprm.ece.icom4035.list;

public class MainTester {

	public static void main(String[] args) {

		//tester de array methods
		//		ArrayList<String> L =  new ArrayList<String>();
		//		System.out.println(L.isEmpty());
		//		System.out.println("---------");
		//		L.add("ted");
		//		System.out.println(L.first());
		//		L.set(0, "Jan");
		//		L.add("bob");
		//		L.add("Ricardo");
		//		System.out.println(L.last());
		//		System.out.println(L.size());
		//		L.add("Jose");
		//		L.add(2, "Messi");
		//		L.remove(2);
		//		L.add(4, "bob");
		//		//L.remove("bob");
		//		System.out.println(L.firstIndex("le"));
		//		System.out.println(L.lastIndex("bob"));
		//		//L.clear();
		//		System.out.println(L.removeAll("bob"));
		//		System.out.println(L.contains("t"));
		//		
		//		
		//tester de linkedList methods
		SinglyLinkedList<String> L = new SinglyLinkedList<String>();

		System.out.println(L.size());
		L.add("BOB");
		L.add("FA");
		System.out.println(L.size());

		L.add(0, "Vale");
		//	L.add(1, "Vale");
		L.add(2,"Jan");
		L.add(4,"Vjan");
		//System.out.println(L.size());
		//		System.out.println(L.remove("Vale"));
		//L.remove(4);
		//	System.out.println(L.size());
		//	System.out.println(L.remove("Vale"));
		//	L.remove(2);
		//	L.add("Jan");
		//		L.removeAll();
		//	System.out.println(L.size());
		//L.add("Jan");
		System.out.println(L.lastIndex("Vjan"));
		//L.clear();

		System.out.println("---------");
		for(int i=0; i<L.size();i++) {
			System.out.println(L.get(i));
		}

	}
}
