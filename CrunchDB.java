import java.util.*;
import java.io.*;

/** 
 * This is responsible for the overall management of the database.
 * CrunchDB should deal with taking input from the user and displaying the correct
 * output while passing off the more complicated work to the corresponding
 * classes.
 */

public class CrunchDB {

	// TODO the following data structures are responsible for storing the
	// entries and snapshots related to this instance of the database. You can
	// modify them if you wish, as long as you do not change any method
	// signatures.
	private List<Entry> entries;
	private List<Snapshot> snapshots;

	public CrunchDB(){ //Initialises the snapshot and entry list so the database can store entries and snapshots
		// TODO: write the constructor.
		this.entries = new ArrayList<Entry>();
		this.snapshots = new ArrayList<Snapshot>();
	}

	/** 
	 * Displays all keys in current state.
	 */
	private void listKeys() {
		// TODO: implement this
		if(this.entries.size() == 0){
			System.out.println("no keys");
		}else{
			for(int i = this.entries.size() - 1; i >= 0; i--){
				System.out.println(this.entries.get(i).getKey()); //Lists in form of most recently added to least
				continue;
			}
		}
	}

	/**
	 * Displays all entries in the current state.
	 */
	private void listEntries() {
		// TODO: implement this
		if(this.entries.size() == 0){
			System.out.println("no entries");
		}else{
			for(int i = Entry.listAllEntries(this.entries).split("\n").length - 1; i >= 0; i--){
				System.out.println(Entry.listAllEntries(this.entries).split("\n")[i]); //Lists in form of most recently added to least
			} //calls static funtion listAllEntries from entry
		}
	}
	
	/**
	 * Displays all snapshots in the current state.
	 */
	private void listSnapshot() {
		// TODO: implement this
		if(this.snapshots.size() == 0){
			System.out.println("no snapshots");
		}else{
			for(int i = Snapshot.listAllSnapshots(this.snapshots).split("\n").length - 1; i >= 0; i--){
				System.out.println(Snapshot.listAllSnapshots(this.snapshots).split("\n")[i]); //Lists in form of most recently added to least
			} //calls static function listAllSnapshots from snapshot
		}
	}

	/**
	 * Displays entry values.
	 *
	 * @param key the key of the entry
	 */
	private void get(String key) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){ //checks if key exists
				found = true;
				System.out.println(this.entries.get(i).get()); //prints the entry values associated with the key, calls get from entry
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}
	
	/**
	 * Deletes entry from current state.
	 *
	 * @param key the key of the entry
	 */
	private void del(String key) { 
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				found = true;
				this.entries.remove(i);
				System.out.println("ok");
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}

	
	private void purgDel(String key) { //extra function created to be used in purge, functions same as del() except the print statements.
		// TODO: implement this
		
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				this.entries.remove(i);
				break;
			}
		}
	}
	/**
	 * Deletes entry from current state and snapshots.
	 *
	 * @param key the key of the entry
	 */
	private void purge(String key) {
		// TODO: implement this	
		this.purgDel(key); //deletes from current state
		for(int i = 0; i < this.snapshots.size(); i++){
			this.snapshots.get(i).removeKey(key); //deletes from snapshot's state
		}
		System.out.println("ok"); //Will output ok regardless of existing
	}

	/**
	 * Sets entry values.
	 *
	 * @param key    the key of the entry
	 * @param values the values to set
	 */
	private void set(String key, List<Integer> values) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				found = true;
				this.entries.set(i, new Entry(key, values)); //if the key is same then it replaces the old key with new values
				break;
			}
		}
		if(found == false){
			this.entries.add(new Entry(key, values)); //creates new entry object
		}
		
		System.out.println("ok");
	}

	/**
	 * Pushes values to the front.
	 *
	 * @param key    the key of the entry
	 * @param values the values to push
	 */
	private void push(String key, List<Integer> values) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				found = true;
				this.entries.get(i).push(values); //calls push from entry
				System.out.println("ok");
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}

	/**
	 * Appends values to the back.
	 *
	 * @param key    the key of the entry
	 * @param values the values to append
	 */
	private void append(String key, List<Integer> values) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				found = true;
				this.entries.get(i).append(values); //calls append from entry
				System.out.println("ok");
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}

	/**
	 * Displays value at index.
	 *
	 * @param key   the key of the entry
	 * @param index the index to display
	 */
	private void pick(String key, int index) {
		// TODO: implement this
	
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				found = true;
				Integer x = this.entries.get(i).pick(index); //calls pick from entry
				if(x == null){
					System.out.println("index out of range");
				}else{
					System.out.println(x);
				}
				break;
			}
		}
		
		if(found == false){
			System.out.println("no such key");
		}
	}
	
	/**
	 * Displays and removes the value at index.
	 *
	 * @param key   the key of the entry
	 * @param index the index to pluck
	 */
	private void pluck(String key, int index) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				found = true;
				Integer x = this.entries.get(i).pluck(index); //calls pluck from entry
				if(x == null){
					System.out.println("index out of range");
				}else{
					System.out.println(x);
				}
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}

	/**
	 * Displays and removes the front value.
	 *
	 * @param key the key of the entry
	 */
	private void pop(String key) {
		// call pop
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				found = true;
				Integer x = this.entries.get(i).pop(); //calls pop from entry
				if(x == null){
					System.out.println("nil");
				}else{
					System.out.println(x);
				}
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}

	/** 
	 * Deletes snapshot.
	 *
	 * @param id the id of the snapshot
	 */
	private void drop(int id) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.snapshots.size(); i++){
			if(id == this.snapshots.get(i).getID()){
				found = true;
				this.snapshots.remove(i);
				System.out.println("ok");
				break;
			}
		}
		if(found == false){
			System.out.println("no such snapshot");
		}
	}

	/**
	 * Restores to snapshot and deletes newer snapshots.
	 *
	 * @param id the id of the snapshot
	 */
	private void rollback(int id) {
		// TODO: implement this
		boolean found = false;
		int j = Integer.MAX_VALUE;
		for(int i = 0; i < this.snapshots.size(); i++){
			if(id == this.snapshots.get(i).getID()){
				found = true;
				this.entries.clear();
				for(int m = 0; m < this.snapshots.get(i).rollback().size(); m++){
					this.entries.add(this.snapshots.get(i).rollback().get(m)); //replaces current entry state with the rollbacked entry state called from rollback in snapshot
				}
				System.out.println("ok");
				j = i; 
			}
			if(j < i){ //removes the newer snapshots created
				this.snapshots.remove(i);
			}
		}
		if(found == false){
			System.out.println("no such snapshot");
		}
	}

	/** 
	 * Replaces current state with a copy of snapshot.
	 *
	 * @param id the id of the snapshot
	 */
	private void checkout(int id) { //same functions of rollback except it does not delete snapshots
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.snapshots.size(); i++){
			if(id == this.snapshots.get(i).getID()){
				found = true;
				this.entries.clear();
				for(int m = 0; m < this.snapshots.get(i).rollback().size(); m++){
					this.entries.add(this.snapshots.get(i).rollback().get(m));
				}
				System.out.println("ok");
			}
		}
		if(found == false){
			System.out.println("no such snapshot");
		}
	}

	/** 
	 * Saves the current state as a snapshot.
	 */
	int x = 1; //unique id begginer
	private void snapshot() {
		// call constructor
		List<Entry> y = new ArrayList<Entry>();
		for(int i = 0; i < this.entries.size(); i++){
			String key = new String(this.entries.get(i).getKey()); //creates a new key of snapshot entry
			List<Integer> value = new ArrayList<Integer>(this.entries.get(i).getVal()); //creates new values of snapshot entry
			y.add(new Entry(key, value)); //holds snapshotted entries
		}
		
		this.snapshots.add(new Snapshot(x, y)); //snapshots created with unique id.
		System.out.println("saved as snapshot " + x);
		x = x + 1; //updating id so it stays unique for the lifetime of a program
	}

	/**
	 * Saves snapshot to file.
	 *
	 * @param id       the id of the snapshot 
	 * @param filename the name of the file
	 */
	private void archive(int id, String filename) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.snapshots.size(); i++){
			if(id == this.snapshots.get(i).getID()){
				found = true;
				this.snapshots.get(i).archive(filename); //calls archive from snapshot
				System.out.println("ok");
				break;
			}
		}
		if(found = false){
			System.out.println("no such snapshot");
		}
	}

	/**
	 * Loads and restores snapshot from file.
	 *
	 * @param filename the name of the file
	 */
	private void restore(String filename) {
		// TODO: implement this
		this.entries = Snapshot.restore(filename); //calls restore from snapshot
		this.snapshots.clear(); //deletes all the existing snapshot
		System.out.println("ok");
	}

	/**
	 * Displays minimum value.
	 *
	 * @param key the key of the entry
	 */
	private void min(String key) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				found = true;
				System.out.println(this.entries.get(i).min()); //calls min from entry
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}

	/**
	 * Displays maximum value.
	 *
	 * @param key the of the entry
	 */
	private void max(String key) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				found = true;
				System.out.println(this.entries.get(i).max()); //calls max from entry
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}

	/**
	 * Displays the sum of values.
	 *
	 * @param key the key of the entry
	 */
	private void sum(String key) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				found = true;
				System.out.println(this.entries.get(i).sum()); //calls sum from entry
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}

	/**
	 * Displays the number of values.
	 *
	 * @param key the key of the entry
	 */
	private void len(String key) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				found = true;
				System.out.println(this.entries.get(i).len()); //calls len from entry
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}

	/**
	 * Reverses the order of values.
	 *
	 * @param key the key of the entry
	 */
	private void rev(String key) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){ //calls rev from entry
				found = true;
				this.entries.get(i).rev();
				System.out.println("ok");
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}

	/**
	 * Removes repeated adjacent values.
	 *
	 * @param key the key of the entry
	 */
	private void uniq(String key) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){ //calls uniq from entry
				found = true;
				this.entries.get(i).uniq();
				System.out.println("ok");
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}

	/** 
	 * Sorts values in ascending order.
	 *
	 * @param key the key of the entry
	 */
	private void sort(String key) {
		// TODO: implement this
		boolean found = false;
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){
				found = true;
				this.entries.get(i).sort(); //calls sort from entry
				System.out.println("ok");
				break;
			}
		}
		if(found == false){
			System.out.println("no such key");
		}
	}

	/**
	 * Displays set difference of values in keys. 
	 * Needs at least two keys.
	 *
	 * @param keys the keys of the entries
	 */
	private void diff(List<String> keys) {
		// TODO: implement this
		List<Entry> list = new ArrayList<Entry>();
		
		for(int i = 0; i < keys.size(); i++){
			boolean found = false;
			for(int j = 0; j < this.entries.size(); j++){
				if(keys.get(i).equals(this.entries.get(j).getKey())){
					found = true;
					String key = new String(this.entries.get(j).getKey());
					List<Integer> value = new ArrayList<>(this.entries.get(j).getVal());
					list.add(new Entry(key, value)); //adds copied entry objects so the original is not affected
					break;
				}
			}
			if(found == false){
				list.clear();
				break;
			}
		}
		
		if(list.size() == 0){
			System.out.println("no such key");
		}else{
			System.out.println(Entry.diff(list).toString().replaceAll(",", "")); //calls static function diff from entry
		}
		
	}

	/**
	 * Displays set intersection of values in keys.
	 * Needs at least two keys.
	 *
	 * @param keys the keys of the entries
	 */
	private void inter(List<String> keys) {
		// TODO: implement this
		List<Entry> list = new ArrayList<Entry>();
		
		for(int i = 0; i < keys.size(); i++){
			boolean found = false;
			for(int j = 0; j < this.entries.size(); j++){
				if(keys.get(i).equals(this.entries.get(j).getKey())){
					found = true;
					String key = new String(this.entries.get(j).getKey());
					List<Integer> value = new ArrayList<>(this.entries.get(j).getVal());
					list.add(new Entry(key, value)); //adds copied entry objects so the original is not affected
					break;
				}
			}
			if(found == false){
				list.clear();
				break;
			}
		}
		
		if(list.size() == 0){
			System.out.println("no such key");
		}else{
			System.out.println(Entry.inter(list).toString().replaceAll(",", "")); //calls static function inter from entry
		}
	}

	/**
	 * Displays set union of values in keys.
	 * Needs at least two keys.
	 *
	 * @param keys the keys of the entries
	 */
	private void union(List<String> keys) {
		// TODO: implement this
		List<Entry> list = new ArrayList<Entry>();
		
		for(int i = 0; i < keys.size(); i++){
			boolean found = false;
			for(int j = 0; j < this.entries.size(); j++){
				if(keys.get(i).equals(this.entries.get(j).getKey())){
					found = true;
					String key = new String(this.entries.get(j).getKey());
					List<Integer> value = new ArrayList<>(this.entries.get(j).getVal());
					list.add(new Entry(key, value)); //adds copied entry objects so the original is not affected
					break;
				}
			}
			if(found == false){
				list.clear();
				break;
			}
		}
		
		if(list.size() == 0){
			System.out.println("no such key");
		}else{
			String s = Entry.union(list).toString(); //calls static function union from entry
			s = s.replaceAll(",", "");
			System.out.println(s);
		}
	}

	/** 
	 * Displays cartesian product of the sets.
	 * Needs at least two keys.
	 *
	 * @param keys the keys of the entries
	 */
	private void cartprod(List<String> keys) {
		// TODO: implement this
		List<Entry> list = new ArrayList<Entry>();
		
		for(int i = 0; i < keys.size(); i++){
			boolean found = false;
			for(int j = 0; j < this.entries.size(); j++){
				if(keys.get(i).equals(this.entries.get(j).getKey())){
					found = true;
					String key = new String(this.entries.get(j).getKey());
					List<Integer> value = new ArrayList<>(this.entries.get(j).getVal());
					list.add(new Entry(key, value)); //adds copied entry objects so the original is not affected
					break;
				}
			}
			if(found == false){
				list.clear();
				break;
			}
		}
		
		if(list.size() == 0){
			System.out.println("no such key");
		}else{
			String s = Entry.cartprod(list).toString().replaceAll(",", "");
			s = s.replace("[[", "[ [");
			s = s.replace("]]", "] ]");
			System.out.println(s); //calls static function cartprod from entry
		}
	}

	private static final String HELP =
		"BYE   clear database and exit\n"+
		"HELP  display this help message\n"+
		"\n"+
		"LIST KEYS       displays all keys in current state\n"+
		"LIST ENTRIES    displays all entries in current state\n"+
		"LIST SNAPSHOTS  displays all snapshots in the database\n"+
		"\n"+
		"GET <key>    displays entry values\n"+
		"DEL <key>    deletes entry from current state\n"+
		"PURGE <key>  deletes entry from current state and snapshots\n"+
		"\n"+
		"SET <key> <value ...>     sets entry values\n"+
		"PUSH <key> <value ...>    pushes values to the front\n"+
		"APPEND <key> <value ...>  appends values to the back\n"+
		"\n"+
		"PICK <key> <index>   displays value at index\n"+
		"PLUCK <key> <index>  displays and removes value at index\n"+
		"POP <key>            displays and removes the front value\n"+
		"\n"+
		"DROP <id>      deletes snapshot\n"+
		"ROLLBACK <id>  restores to snapshot and deletes newer snapshots\n"+
		"CHECKOUT <id>  replaces current state with a copy of snapshot\n"+
		"SNAPSHOT       saves the current state as a snapshot\n"+
		"\n"+
		"ARCHIVE <id> <filename> saves snapshot to file\n"+
		"RESTORE <filename> loads snapshot from file\n"+
		"\n"+
		"MIN <key>  displays minimum value\n"+
		"MAX <key>  displays maximum value\n"+
		"SUM <key>  displays sum of values\n"+
		"LEN <key>  displays number of values\n"+
		"\n"+
		"REV <key>   reverses order of values\n"+
		"UNIQ <key>  removes repeated adjacent values\n"+
		"SORT <key>  sorts values in ascending order\n"+
		"\n"+
		"DIFF <key> <key ...>   displays set difference of values in keys\n"+
		"INTER <key> <key ...>  displays set intersection of values in keys\n"+
		"UNION <key> <key ...>  displays set union of values in keys\n"+
		"CARTPROD <key> <key ...>  displays set union of values in keys";
	
	public static void bye() {
		System.out.println("bye");
	}
	
	public static void help() {
		System.out.println(HELP);
	}
	
	
	public static void main(String[] args) {
		// TODO: Your main method here!
		Scanner in = new Scanner(System.in);
		CrunchDB c = new CrunchDB();
		
		while(true){
			System.out.print("> ");
			String input = in.nextLine();
			
			if(input.equalsIgnoreCase("BYE")){
				c.bye();
				break;
			}
			
			if(input.equalsIgnoreCase("HELP")){
				c.help();
				System.out.println();
				continue;
			}
			
			if(input.equalsIgnoreCase("LIST KEYS")){
				c.listKeys();
				System.out.println();
				continue;
			}
			
			if(input.equalsIgnoreCase("LIST ENTRIES")){
				c.listEntries();
				System.out.println();
				continue;
			}
			
			if(input.equalsIgnoreCase("LIST SNAPSHOTS")){
				c.listSnapshot();
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("GET")){ //splits input to get correct input parameters to be passed to the methods
				c.get(input.split(" ")[1]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("DEL")){ //splits input to get correct input parameters to be passed to the methods
				c.del(input.split(" ")[1]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("PURGE")){ //splits input to get correct input parameters to be passed to the methods
				c.purge(input.split(" ")[1]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("SET")){ //splits input to get correct input parameters to be passed to the methods
				String key = input.split(" ")[1]; //holds key for entry
				List<Integer> values = new ArrayList<Integer>();
				
				for(int i = 2; i < input.split(" ").length; i++){
					int x = Integer.parseInt(input.split(" ")[i]); //convert string to int
					Integer y = new Integer(x);
					values.add(y); //holds values of entry
				}
				
				c.set(key, values);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("PUSH")){ //splits input to get correct input parameters to be passed to the methods
				String key = input.split(" ")[1];
				
				List<Integer> values = new ArrayList<Integer>();
				
				for(int i = 2; i < input.split(" ").length; i++){
					int x = Integer.parseInt(input.split(" ")[i]);
					Integer y = new Integer(x);
					values.add(y);
				}
				c.push(key, values);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("APPEND")){
				String key = input.split(" ")[1];
				
				List<Integer> values = new ArrayList<Integer>();
				
				for(int i = 2; i < input.split(" ").length; i++){
					int x = Integer.parseInt(input.split(" ")[i]);
					Integer y = new Integer(x);
					values.add(y);
				}
				c.append(key, values);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("PICK")){
				String key = input.split(" ")[1];
				int x = Integer.parseInt(input.split(" ")[2]);
				c.pick(key, x);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("PLUCK")){
				String key = input.split(" ")[1];
				int x = Integer.parseInt(input.split(" ")[2]);
				c.pluck(key, x);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("POP")){
				c.pop(input.split(" ")[1]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("DROP")){
				int x = Integer.parseInt(input.split(" ")[1]);
				c.drop(x);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("ROLLBACK")){
				int x = Integer.parseInt(input.split(" ")[1]);
				c.rollback(x);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("CHECKOUT")){
				int x = Integer.parseInt(input.split(" ")[1]);
				c.checkout(x);
				System.out.println();
				continue;
			}
			
			if(input.equalsIgnoreCase("SNAPSHOT")){
				c.snapshot();
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("ARCHIVE")){
				int x = Integer.parseInt(input.split(" ")[1]);
				c.archive(x, input.split(" ")[2]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("RESTORE")){
				c.restore(input.split(" ")[1]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("MIN")){
				c.min(input.split(" ")[1]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("MAX")){
				c.max(input.split(" ")[1]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("SUM")){
				c.sum(input.split(" ")[1]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("LEN")){
				c.len(input.split(" ")[1]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("REV")){
				c.rev(input.split(" ")[1]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("UNIQ")){
				c.uniq(input.split(" ")[1]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("SORT")){
				c.sort(input.split(" ")[1]);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("DIFF")){
				List<String> keys = new ArrayList<String>();
				
				for(int i = 1; i < input.split(" ").length; i++){
					keys.add(input.split(" ")[i]);
				}
				
				c.diff(keys);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("INTER")){
				List<String> keys = new ArrayList<String>();
				
				for(int i = 1; i < input.split(" ").length; i++){
					keys.add(input.split(" ")[i]);
				}
				
				c.inter(keys);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("UNION")){
				List<String> keys = new ArrayList<String>();
				
				for(int i = 1; i < input.split(" ").length; i++){
					keys.add(input.split(" ")[i]);
				}
				
				c.union(keys);
				System.out.println();
				continue;
			}
			
			if(input.split(" ")[0].equalsIgnoreCase("cartprod")){
				List<String> keys = new ArrayList<String>();
				
				for(int i = 1; i < input.split(" ").length; i++){
					keys.add(input.split(" ")[i]);
				}
				
				c.cartprod(keys);
				System.out.println();
				continue;
			}
			else{
				System.out.println("Invalid input. Please type 'HELP' for more information");
				System.out.println();
				continue;
			}
		}
	}
}
