### 파일 ###

BufferedReader br = new BufferedReader(new FileReader(".\\DS_Sample2.csv"));
String line;
while ((line = br.readLine()) != null) 
{
	// Some Code
}

### 정렬 ###

ArrayList<String> al = new ArrayList<String>();

// 오름차순 정렬
Collections.sort(al);

// 내림차순 Comparator
Comparator<String> co = new Comparator<String>() {
	public int compare(String o1, String o2) {
	return (o2.compareTo(o1));
}

// 내림차순
Collections.sort(al, co);
Collections.sort(al, (g1, g2) --> g1.compareTo(g2));

### Queue ###
Queue<String> q = new LinkedList<>();
Deque<String> deq = new ArrayDeque<>();


### 프로세스 ###
public static String getProcessOutput (List<String> cmdList ) throws IOException , InterruptedException
{
	ProcessBuilder builder = new ProcessBuilder(cmdList); 
	Process process = builder.start();
	InputStream psout = process.getInputStream();
	byte[] buffer = new byte[1024];
    int len = psout.read(buffer);
    return (new String(buffer, 0, len));
}

public static void main(String[] args) throws IOException , InterruptedException
{
	String output = getProcessOutput(Arrays.asList ("add_2sec.exe","2","3"));
	System.out.println(output);
}

### 쓰레드 ###
extends Thread 
implements Runnable 

## thread 정의
Thread thread1 = new Thread(new Runnable() {
	@Override
	public void run() {
		System.out.println("thread");
	}
});	
thread1.start();

## Lamda
Thread thread2 = new Thread(() -> {
	System.out.println("thread");
	});
thread2.start();

### Synchronization

##Mutex
import java.util.concurrent.locks.ReentrantLock;

class ThreadClass implements Runnable{
	static ReentrantLock lock = new ReentrantLock
	public void run(){
		lock.lock
		try {
			SaveFile(data);
		}
		finally {
			lock.unlock();
		}
	}
}
public class ThreadRunnable {
	public static void main(String[] args) {
		Thread t1 = new Thread(new ThreadClass ());
		t1.start();
		
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

### Encryption_Decryption
// Base64
void Base64Sample(String TestString ) throws UnsupportedEncodingException {
	Encoder encoder = Base64.getEncoder();
	String encodedString = encoder.encodeToString(TestString.getBytes("UTF-8"));
	System.out.println(encodedString);

	Decoder decoder = Base64.getDecoder();
	byte[] decodedBytes = decoder.decode(encodedString);
	String decodedString = new String( decodedBytes , "UTF-8");
	System.out.println(decodedString);
}

// SHA256
void SHA256(String input) throws NoSuchAlgorithmException
{
	MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
	byte[] result = mDigest.digest(input.getBytes());

	StringBuffer sb = new StringBuffer();

	for (int i = 0; i < result.length ; i ++) {
		sb.append(Integer.toString (result[i] & 0xFF + 0x100, 16).substring(1));
	}
	System.out.println(sb.toString());
}

### Network
// Server
public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        ServerSocket listener = new ServerSocket(9090);
        try {
            Socket socket = listener.accept();
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("test");
            } finally {
                socket.close();
            }
        }
        finally {
            listener.close();
        }
    }
}
// Client
public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 9090);
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        System.out.println(answer);
    }
}