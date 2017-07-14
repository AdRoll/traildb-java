
import traildb.TrailDB;
import traildb.TrailDBConstructor;
import traildb.TrailDBCursor;
import traildb.TrailDBEvent;

import java.util.UUID;

public class Example {

	public static void read() {
		TrailDB tdb = new TrailDB("tiny.tdb");
		TrailDBCursor cur = tdb.cursorNew();
		long numCookies = tdb.numTrails();
		UUID uuid;
		TrailDBEvent event;
		for (int i=0; i < numCookies; i++) {
			// uuid = tdb.getUUID(i);
			// System.out.println(uuid);
			cur.getTrail(i);
			while ((event = cur.next()) != null) {
				System.out.println(event.timestamp + " " + event.getItem(0));
			}
		}
	}

	public static void main(String[] args) {
		TrailDBConstructor cons = new TrailDBConstructor("tiny", new String[] {"user", "action"});

		UUID cookie1 = UUID.randomUUID();
		UUID cookie2 = UUID.randomUUID();

		System.out.println(cookie1 + " " + cookie2);

		cons.add(cookie1, 1, new String[] {"bob", "run"});
		cons.add(cookie2, 2, new String[] {"fred", "walk"});
		cons.add(cookie1, 4, new String[] {"jerry", "speak"});
		cons.add(cookie1, 5, new String[] {"ted", "fly"});

		cons.finalize();
		cons.close();

		System.out.println("Finished writing");
		read();
	}
}