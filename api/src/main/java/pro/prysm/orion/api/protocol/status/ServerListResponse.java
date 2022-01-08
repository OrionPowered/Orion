package pro.prysm.orion.api.protocol.status;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import lombok.Data;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Data
public class ServerListResponse {
    private VersionResponse version = new VersionResponse();
    private PlayersResponse players = new PlayersResponse();
    private Component description;
    private String favicon;

    public static String generateFavicon(InputStream favicon) throws IOException {
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(favicon.readAllBytes());
    }

    public String toJsonString() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonSerializer<Component> serializer = (src, typeOfSrc, context) ->
                GsonComponentSerializer.gson().serializeToTree(src);
        gsonBuilder.registerTypeAdapter(Component.class, serializer);

        return gsonBuilder.create().toJson(this);
    }
}



