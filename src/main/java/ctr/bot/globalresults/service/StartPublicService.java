package ctr.bot.globalresults.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import ctr.bot.globalresults.dto.Tunnel;
import ctr.bot.globalresults.dto.Tunnels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ctr.bot.globalresults.entity.ParametersSchemas;
import ctr.bot.globalresults.repository.ParametersRespository;


@Service
public class StartPublicService {

    @Autowired
    private ParametersRespository parametersRespository;

    public static boolean RUNNING = false;
    public static Process process;

    public String startPublic() {
        String command = "ngrok http 8080";
        System.out.println("Starting...");
        return runCommand(command);
    }

    private String runCommand (String command) {
        String description = "server-global-rank";
        String server = "";
        try {
            process = Runtime.getRuntime().exec(command);
            server = getUrl();
            System.out.println(server);
            ParametersSchemas parametersSchema = parametersRespository.findByDescription(description);
            parametersSchema.setName(server);
            parametersRespository.save(parametersSchema == null ? new ParametersSchemas(server, description) : parametersSchema);
        } catch(IOException e) {
            System.out.println(e.getMessage());
            process.destroy();
        }

        return server;
    }

    private String getUrl() throws IOException {
        URL url = new URL("http://127.0.0.1:4040/api/tunnels");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);
        con.connect();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return getHttps(new Gson().fromJson(content.toString(), Tunnels.class));
    }

    private String getHttps(Tunnels tunnels) {
        for (Tunnel tunnel : tunnels.tunnels) {
            if("https".equals(tunnel.proto)) {
                return tunnel.public_url;
            }
        }
        return "";
    }
}
