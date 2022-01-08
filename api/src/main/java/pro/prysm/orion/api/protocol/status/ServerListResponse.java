package pro.prysm.orion.api.protocol.status;

import com.google.gson.*;
import lombok.Data;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

@Data
public class ServerListResponse {
    private VersionResponse version = new VersionResponse();
    private PlayersResponse players = new PlayersResponse();
    private Component description;
    private String favicon;

    public String toJsonString() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        JsonSerializer<Component> serializer = (src, typeOfSrc, context) ->
                GsonComponentSerializer.gson().serializeToTree(src);
        gsonBuilder.registerTypeAdapter(Component.class, serializer);

        return gsonBuilder.create().toJson(this);
    }
}



