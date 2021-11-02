import java.util.*;
import java.io.*;

/**
 * Snapshot deals with storing the id and current state of the database. 
 * As well as storing this data, the Snapshot class should manage operations
 * related to snapshots.
 */

public class Snapshot {
	private int id;
	private List<Entry> entries;

	public Snapshot(int id, List<Entry> entries) {
		// TODO: write this constructor
		this.id = id;
		this.entries = entries;
	}
	
	public int getID(){
		return this.id;
	}
	
	public List<Entry> getEntry(){
		return this.entries;
	}
	/**
	 * Finds and removes the key.
	 *
	 * @param key the key to remove
	 */
	public void removeKey(String key) {
		// TODO: implement this
		
		for(int i = 0; i < this.entries.size(); i++){
			if(key.equals(this.entries.get(i).getKey())){ //Searches and looks if the key exists or not
				this.entries.remove(i);
				break;
			}
		}
	}

	
	public List<Entry> rollback() {
		// TODO: implement this
		return this.entries; //Returns the entries associated with this snapshot
	}


	/**
	 * Saves the snapshot to file.
	 *
	 * @param filename the name of the file
	 */
	public void archive(String filename) {
		// TODO: implement this
		try{
			PrintWriter out = new PrintWriter(filename);
			for(int i = 0; i < this.entries.size(); i++){
				String s = this.entries.get(i).getKey() + "|"; //formats display
				String m = "";
				String f = "";
				for(int j = 0; j < this.entries.get(i).getVal().size(); j++){
					if(j == this.entries.get(i).getVal().size() - 1){
						m += Integer.toString(this.entries.get(i).getVal().get(j).intValue());
						break;
					}
					m += Integer.toString(this.entries.get(i).getVal().get(j).intValue()) + ","; //formats display
				}
				f = s + m;
				out.print(f);
				out.println();
			}
			out.close();
			
		}catch(FileNotFoundException e){
			
		}
		
	}

	/**
	 * Loads and restores a snapshot from file.
	 *
	 * @param  filename the name of the file
	 * @return          the list of entries in the restored state
	 */
	public static List<Entry> restore(String filename) {
		// TODO: implement this
		List<Entry> restored = new ArrayList<Entry>();
		List<Integer> list = new ArrayList<Integer>();
		
		try{
			Scanner in = new Scanner(new File(filename));
			
			while(in.hasNextLine()){
				String s = in.nextLine();
				if(s.equals("")){
					break;
				}
				String t = s.replace("|", ","); //formats display
				t = t.replaceAll(",", ""); //formats display
				String[] a = t.split(""); 
				String m = "";
				
				for(int i = 0; i < a.length; i++){
					if(i == 0){
						m = a[i]; //holds the entry's key
					}
					else{
						int x = Integer.parseInt(a[i]);
						Integer y = new Integer(x);
						list.add(y); //holds the entry's values
					}
				}
				List<Integer> k = new ArrayList<Integer>(list);
				restored.add(new Entry(m, k)); //entry restored as new entry object
				list.clear();
			}
			in.close();
			
		}catch(FileNotFoundException e){
			return null;
		}
		
		return restored;
	}

	/**
	 * Formats all snapshots for display.
	 *
	 * @param  snapshots the snapshots to display
	 * @return           the snapshots ready to display
	 */
	public static String listAllSnapshots(List<Snapshot> snapshots) {
		// TODO: implement this
		String r = "";
		for(int i = 0; i < snapshots.size(); i++){
			if(i == snapshots.size() - 1){
				String s = Integer.toString(snapshots.get(i).getID()); //converts int to string for display
				r += s;
				break;
			}
			String s = Integer.toString(snapshots.get(i).getID());
			r += s + "\n"; //formats display
		}
		return r;
	}
}
