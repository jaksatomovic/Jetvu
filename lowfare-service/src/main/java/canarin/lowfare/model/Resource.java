package canarin.lowfare.model;

import canarin.lowfare.payload.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

public class Resource {

    protected Resource() {}

    private @Getter Response response;
    private @Getter Class deSerializationClass;

    public static Resource[] fromArray(Response response, Class klass) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Resource[] resources = (Resource[]) gson.fromJson(response.getData(), klass);
        for (Resource resource : resources) {
            resource.response = response;
            resource.deSerializationClass = klass;
        }
        return resources;
    }

    public static Resource fromObject(Response response, Class klass) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Resource resource = (Resource) gson.fromJson(response.getData(), klass);
        resource.response = response;
        resource.deSerializationClass = klass;
        return resource;
    }
}
