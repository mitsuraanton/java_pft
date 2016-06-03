package ru.stqa.pft.rest;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by Anton.Mitsura on 02.06.2016.
 */
public class TestBase {
    boolean isIssueOpen(int issueId) throws IOException, ServiceException {
        String json = Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "").execute(Request.Post("http://demo.bugify.com/api/issues/following/" + issueId + ".json")
                .bodyForm(new BasicNameValuePair("method", "follow")))
                .returnContent().asString();
        Gson gson = new GsonBuilder().create();
        JsonObject job = gson.fromJson(json, JsonObject.class);
        JsonArray issues=job.getAsJsonArray("issues");
        JsonObject jsonElement = issues.get(0).getAsJsonObject();
        String resolve = jsonElement.getAsJsonObject().get("resolved").getAsString();
        if(resolve.equals("1970-01-01T00:00:00+00:00")) return true;
        else return false;
    }

    public void skipIfNotFixed(int issueId) throws IOException, ServiceException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
