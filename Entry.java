import java.util.*;
import java.io.*;

/**
 * Entry deals with storing the key and value associated with entries in the
 * database. 
 * As well as storing the data the entry class should manage operations
 * associated with any Entry.
 */

public class Entry {
	private String key;
	private List<Integer> values;

	public Entry(String key, List<Integer> values) {
		//constructor for Entry object
		this.key = key;
		this.values = values;
	}

	/**
	 * Formats the Entry for display
	 *
	 * @return  the String of values
	 */
	public String getKey(){
		return this.key;
	}
	
	
	public String get() {
		// TODO: implement this
		String s = this.values.toString();
		s = s.replaceAll(",", ""); //formats display
		return s;
	}
	
	public List<Integer> getVal(){
		return this.values;
	}

	/**
	 * Sets the values of this Entry.
	 *
	 * @param values the values to set
	 */
	public void set(List<Integer> values) {
		// TODO: implement this
		this.values = values;
	}

	/**
	 * Adds the values to the start.
	 *
	 * @param values the values to add
	 */
	public void push(List<Integer> values) {
		// TODO: implement this
		for(int i = 0; i < values.size(); i ++){
			this.values.add(0, values.get(i)); //adds each value to index 0 of the values of this entry
		}
	}

	/**
	 * Adds the values to the end.
	 *
	 * @param values the values to add
	 */
	public void append(List<Integer> values) {
		// TODO: implement this
		for(int i = 0; i < values.size(); i++){
			this.values.add(values.get(i)); //adds each value to the list
		}
	}

	/**
	 * Finds the value at the given index.
	 *
	 * @param  index the index
	 * @return       the value found 
	 */
	public Integer pick(int index) {
		// TODO: implement this
		if(index == 0){ //Due to index starting from 1
			return null; 
		}
		
		if(index > this.values.size()){
			return null;
		}
		
		return this.values.get(index - 1); //Due to index in java starting from 0 but 1 for entry
	}

	/**
	 * Finds and removes the value at the given index.
	 *
	 * @param  index the index
	 * @return       the value found
	 */
	public Integer pluck(int index) {
		if(index == 0){ //Due to index starting from 1
			return null;
		}
		
		if(index > this.values.size()){
			return null;
		}
		
		Integer n = this.values.get(index - 1); 
		this.values.remove(index - 1); //Due to index in java starting from 0 but 1 for entry
		return n;
		
	}

	/**
	 * Finds and removes the first value.
	 *
	 * @return the first value
	 */
	public Integer pop() {
		if(this.values.size() == 0){
			return null;
		}
		Integer removed = this.values.get(0);
		this.values.remove(0);
		return removed;
	}

	/**
	 * Finds the minimum value.
	 *
	 * @return the minimum value
	 */
	public Integer min() {
		// TODO: implement this
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < this.values.size(); i++){
			if(this.values.get(i).intValue() < min){ //Checks if the number is smaller
				min = this.values.get(i).intValue(); //Updates the minimum value
			}
		}
		Integer mn = new Integer(min); //Converts to integer for return type
		return mn;
	}

	/**
	 * Finds the maximum value.
	 *
	 * @return the maximum value
	 */
	public Integer max() {
		// TODO: implement this
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < this.values.size(); i++){ 
			if(this.values.get(i).intValue() > max){ //Checks if the number is larger
				max = this.values.get(i).intValue(); //Updates the maximum value
			}
		}
		Integer mx = new Integer(max); //Converts to integer for return type
		return mx;
	}

	/**
	 * Computes the sum of all values.
	 *
	 * @return the sum
	 */
	public Integer sum() {
		// TODO: implement this
		int sum = 0;
		for(int i = 0; i < this.values.size(); i++){
			sum += this.values.get(i).intValue(); //Updates sum
		}
		Integer sm = new Integer(sum);
		return sm;
	}

	/**
	 * Finds the number of values.
	 *
	 * @return the number of values.
	 */
	public Integer len() {
		// TODO: implement this
		return this.values.size();
	}

	/**
	 * Reverses the order of values.
	 */
	public void rev() {
		// TODO: implement this
		List<Integer> val = new ArrayList<Integer>(this.values); //copies original values
		this.values.clear(); //clears for adding reversed values
		for(int i = val.size() - 1; i >= 0; i--){
			this.values.add(val.get(i)); //values added from the last from the copy of original
		}
		
	}
	
	/**
	 * Removes adjacent duplicate values.
	 */
	public void uniq() {
		// TODO: implement this
		for(int i = 0; i < this.values.size(); i++){
			if(i == this.values.size() - 1){ //Breaks from this condition as there will not be any next value
				break;
			}
			if(this.values.get(i).intValue() == this.values.get(i+1).intValue()){ //Checks the adjecent duplicates
				this.values.remove(i);
				i--;
			}
		}
	}

	/**
	 * Sorts the list in ascending order.
	 */
	public void sort() {
		// TODO: implement this
		int min;
		int ind;
		List<Integer> x = new ArrayList<Integer>(this.values); //copies original list
		this.values.clear(); //clears original list for adding sorted values
		while(!x.isEmpty()){ //loops until all values are sorted
			min = Integer.MAX_VALUE;
			ind = 0;
			for(int i = 0; i < x.size(); i++){
				if(x.get(i) < min){ //Checks if the value is smaller than the minimum
					min = x.get(i); //Updates the value of the minimum
					ind = i; //Updates the index of the minimum value
				}
			}
			this.values.add(x.get(ind)); //Adds the minimum value
			x.remove(ind); //Removes the minimum value from the copied list
		}
	}

	/**
	 * Computes the set difference of the entries.
	 *
	 * @param  entries the entries
	 * @return         the resulting values
	 */
	
	public static boolean contains(List<Integer> a, int element) { //Extra function created that are used in union and diff
		for(int i = 0; i < a.size(); i++){
			if(a.get(i).intValue() == element){
				return true; //returns true if an element exists in the List a.
			}
		}
		return false;
	}
	
	
	public static List<Integer> setSort(List<Integer> set){ //Extra function created that are used in diff, union and inter.
		int min;
		int ind;
		List<Integer> x = new ArrayList<Integer>(set); 
		set.clear();
		while(!x.isEmpty()){ //Does the same selection sort process as sort() function
			min = Integer.MAX_VALUE;
			ind = 0;
			for(int i = 0; i < x.size(); i++){
				if(x.get(i) < min){
					min = x.get(i);
					ind = i;
				}
			}
			set.add(x.get(ind));
			x.remove(ind);
		}
		return set;
	}
	
	public static List<Integer> setUniq(List<Integer> set) { //Extra function created that are used in diff, union and inter.
		// TODO: implement this
		for(int i = 0; i < set.size(); i++){
			for(int j = i + 1; j < set.size(); j++){ //compares the first value to the entire list and continues the process till all the values are compared
				if(set.get(i).intValue() == set.get(j).intValue()){
					set.remove(j); // removes if the value has been duplicated
				}
			}
		}
		return set;
	}
	
	
	public static List<Integer> diff(List<Entry> entries) {
		// TODO: implement this
		if(entries.size() == 1){ //Cases of only one entry list
			return entries.get(0).getVal(); //Returns the list as it is as it cannot be compared to anything
		}
		
		
		List<Integer> diff = new ArrayList<Integer>();
		List<Entry> copy = new ArrayList<Entry>(entries);
		
		for(int i = 0; i < copy.size(); i++){ //Removes duplicates from each orginal list in entrires
			for(int c = 0; c < copy.get(i).getVal().size(); c++){
				diff.add(setUniq(copy.get(i).getVal()).get(c)); //All of the values are added in one single list
			}
		}
		
		for(int j = 0; j < diff.size(); j++){
			int count = 0;
			for(int k = j + 1; k < diff.size(); k++){
				if(diff.get(j).intValue() == diff.get(k).intValue()){ //Checks if that value exists anywhere
					diff.remove(k); //removes the value as it must have been a intersection value
					k--; //updates list counter to avoid skipping values
					count++;
				}
			}
			if(count != 0){ 
				diff.remove(j); //The value being compared to the entire list gets removed if it had found and removed an intersection value in the above loop
				j--; //updates list counter to avoid skipping values
			}
		}
	
		return setSort(diff); //retruns the sorted values.
	} 
	
	/**
	 * Computes the set intersection of the entries.
	 *
	 * @param  entries the entries
	 * @return         the resulting values
	 */
	public static List<Integer> inter(List<Entry> entries) {
		// TODO: implement this
		if(entries.size() == 1){ //Case of one list
			return entries.get(0).getVal();
		}
		
		List<List<Integer>> in = new ArrayList<List<Integer>>();
		
		List<Entry> copy = new ArrayList<Entry>(entries);
		
		for(int x = 0; x < copy.size(); x++){
			in.add(setUniq(copy.get(x).getVal())); //removes duplicates as intersection list concern with unique values
		}
		
		List<Integer> inter = new ArrayList<Integer>();
		
		for(int i = 0; i < in.get(0).size(); i++){ //Compared from the first list only
			int count = 1;
			int value = in.get(0).get(i).intValue();
			inter.add(in.get(0).get(i)); //value added but will be compared
			for(int j = 1; j < in.size(); j++){
				for(int k = 0; k < in.get(j).size(); k++){
					if(value == in.get(j).get(k).intValue()){ //checks if the value exists in any of the following lists
						count++; //updates counter
					}
				}
			}
			if(count != in.size()){ //determines if the value has occured once in each list
				inter.remove(in.get(0).get(i)); //if the value hasn't occured in each list then it gets removed otherwise it is kept for returning
			}
		}
		
		return setSort(inter); //returns the sorted intersection
	}

	/**
	 * Computes the set union of the entries.
	 *
	 * @param  entries the entries
	 * @return         the resulting values
	 */
	public static List<Integer> union(List<Entry> entries) {
		// TODO: implement this
		if(entries.size() == 1){ //Case of one list
			return entries.get(0).getVal();
		}
		
		List<Integer> uni = new ArrayList<Integer>();
		List<Entry> copy = new ArrayList<Entry>(entries);
		for(int i = 0; i < copy.size(); i++){
			for(int j = 0; j < copy.get(i).len(); j++){
				if(!contains(uni, copy.get(i).getVal().get(j).intValue())){ //Checks if the element already contains in teh list.
					uni.add(copy.get(i).getVal().get(j)); //element added for union if it wasn't found in the list
				}
			}
		}
		return setUniq(setSort(uni));
	}

	/**
	 * Computes the Cartesian product of the entries.
	 *
	 * @param  entries the entries
	 * @return         the resulting values
	 */
	public static List<List<Integer>> cartprod(List<Entry> entries) {
		// TODO: implement this
		if(entries.size() == 1 && entries.get(0).getVal().size() == 0){
			List<List<Integer>> finalcart = new ArrayList<List<Integer>>();
			List<Integer> list = new ArrayList<Integer>();
			finalcart.add(list);
			return finalcart;
		}
		
		for(int x = 0; x < entries.size(); x++){ //Removes an empty entry from entries list so it does not gets involve in cartesian calculations
			if(entries.get(x).getVal().size() == 0){
				entries.remove(x);
			}
		}
		
		if(entries.size() == 1){ //Case of when there is only 1 entry
			List<List<Integer>> finalcart = new ArrayList<List<Integer>>();
			finalcart.add(entries.get(0).getVal());
			return finalcart;
		}
		
		
		List<List<Integer>> finalcart = new ArrayList<List<Integer>>();

		for(int i = 0; i < entries.size(); i++){
			List<List<Integer>> cartpro = new ArrayList<List<Integer>>();
			if(finalcart.isEmpty()){ //Executes only the first time for the first two lists
				for(int j = 0; j < entries.get(0).getVal().size(); j++){
					for(int k = 0; k < entries.get(1).getVal().size(); k++){
						List<Integer> list = new ArrayList<Integer>(); //This list adds the corresponding values according to cartesian products
						list.add(entries.get(0).getVal().get(j));
						list.add(entries.get(1).getVal().get(k));
						cartpro.add(list); //Temporary list that holds the current cartesian products
					}
				}
				i++; //Modifies the increment so that the next list calculations are not mismatched
			}else{
				for(int m = 0; m < finalcart.size(); m++){ //Happens for each list and continues until the end
					for(int l = 0; l < entries.get(i).getVal().size(); l++){ //
						List<Integer> list = new ArrayList<Integer>(finalcart.get(m)); //List gets updated with new values each time
						list.add(entries.get(i).getVal().get(l));
						cartpro.add(list); //Temporary list that holds the current cartesian products
					}
				}
				
			}
			
			finalcart = cartpro; //Updates the final cartesian product list each time it has passed a list of entry
		}
		
		return finalcart;
	}

	/**
	 * Formats all the entries for display.
	 *
	 * @param  entries the entries to display
	 * @return         the entries with their values
	 */
	public static String listAllEntries(List<Entry> entries) { 
		// TODO: implement this
		String r = "";
		for(int i = 0; i < entries.size(); i++){
			if(i == entries.size() - 1){
				r += entries.get(i).getKey() + " " + entries.get(i).get(); //formats display
				break;
			}
			r += entries.get(i).getKey() + " " + entries.get(i).get() + "\n"; //formats display
		}
		return r;
	}

}
