import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Executer {
    private final BufferedReader response;
    private final BufferedOutputStream request;

    public Executer(String path) throws IOException {
        Process process = new ProcessBuilder(path).start();
        response = new BufferedReader(new InputStreamReader(process.getInputStream()));
        request = new BufferedOutputStream(process.getOutputStream());
    }

    public void sendRequest(String ... reqs) throws IOException {
        for (String req : reqs)
            request.write(req.getBytes());
        request.flush();
    }

    public String receiveResponse() throws IOException {
        return response.readLine();
    }
}
