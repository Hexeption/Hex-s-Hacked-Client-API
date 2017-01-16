package uk.co.hexeption.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Hexeption on 16/01/2017.
 */

public class API {

    /**
     * Hex's API
     * <p>
     * 1). Client Branding. i.e CLIENT NAME, CLIENT VERSION, MINECRAFT VERSION, OPTIFINE VERSION, CREATOR, WEBSITE. [x]
     * 2). Module System. [x]
     * 3). Render Methods. i.e DRAWRECT, DRAWCIRCLE, DRAWHORIZONTALlINE, [O]
     * 4). Maths Methods. [O]
     * 5). Add MC to Module []
     * 6). TODO: Code a faster EventSystem
     */

    //<editor-fold desc="=== Client Info ===">
    private String clientName = getClass().getAnnotation(ClientInfo.class).CLIENTNAME();

    private String clientVersion = getClass().getAnnotation(ClientInfo.class).CLIENTVERSION();

    private String minecraftVersion = getClass().getAnnotation(ClientInfo.class).MINECRAFTVERSION();

    private String optifineVersion = getClass().getAnnotation(ClientInfo.class).OPTIFINEVERSION();

    private String[] creators = getClass().getAnnotation(ClientInfo.class).CREATORS();

    private String website = getClass().getAnnotation(ClientInfo.class).WEBSITE();


    @Retention(RetentionPolicy.RUNTIME)
    public @interface ClientInfo {

        String CLIENTNAME();

        String CLIENTVERSION() default "1.0";

        String MINECRAFTVERSION() default "";

        String OPTIFINEVERSION() default "";

        String[] CREATORS() default "Hexeption";

        String WEBSITE() default "";

    }

    public String getClientName() {

        return clientName;
    }

    public void setClientName(String clientName) {

        this.clientName = clientName;
    }

    public String getClientVersion() {

        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {

        this.clientVersion = clientVersion;
    }

    public String getMinecraftVersion() {

        return minecraftVersion;
    }

    public void setMinecraftVersion(String minecraftVersion) {

        this.minecraftVersion = minecraftVersion;
    }

    public String getOptifineVersion() {

        return optifineVersion;
    }

    public void setOptifineVersion(String optifineVersion) {

        this.optifineVersion = optifineVersion;
    }

    public String[] getCreators() {

        return creators;
    }

    public void setCreators(String[] creators) {

        this.creators = creators;
    }

    public String getWebsite() {

        return website;
    }

    public void setWebsite(String website) {

        this.website = website;
    }
    //</editor-fold>

}
