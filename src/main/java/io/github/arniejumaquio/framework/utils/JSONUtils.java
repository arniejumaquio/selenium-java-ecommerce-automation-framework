package io.github.arniejumaquio.framework.utils;

import io.github.arniejumaquio.framework.config.ConfigReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.util.JsonUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class JSONUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JSONUtils(){

    }

    public static List<HashMap<String,String>> getDataFromJsonFile(String jsonFilePath) throws IOException {

        //json file to string
        File jsonFile = new File(jsonFilePath);
        String jsonString =  FileUtils.readFileToString(jsonFile);
        //string to hashmap

        List<HashMap<String,String>>  data =   OBJECT_MAPPER.readValue(jsonString, new TypeReference<List<HashMap<String, String>>>() {});

        return  data;
    }


    private static JsonNode resolveVariables(JsonNode jsonNode) {

        if (jsonNode.isTextual()) {

            String text = jsonNode.asText();

            if ("${SAUCE_USERNAME}".equals(text) || "${SAUCE_PASSWORD}".equals(text)) {

                String variableName =
                        text.substring(2, text.length() - 1);

                String resolvedValue;
                try {
                    resolvedValue = ConfigReader.get(variableName);
                } catch (IllegalArgumentException e) {
                    throw new IllegalStateException(
                            "Missing required credential configuration: " + variableName
                    );
                }

                return TextNode.valueOf(resolvedValue);
            }

            return jsonNode;
        }

        if (jsonNode.isObject()) {

            ObjectNode object = (ObjectNode) jsonNode;

            Iterator<String> fieldNames = object.fieldNames();

            while (fieldNames.hasNext()) {

                String fieldName = fieldNames.next();

                JsonNode resolvedValue = resolveVariables(object.get(fieldName));

                object.set(fieldName, resolvedValue);
            }

            return object;
        }

        if (jsonNode.isArray()) {

            ArrayNode array = (ArrayNode) jsonNode;

            for (int i = 0; i < array.size(); i++) {

                JsonNode resolvedValue =
                        resolveVariables(array.get(i));

                array.set(i, resolvedValue);
            }

            return array;
        }

        return jsonNode;
    }

    public static <T> List<T> readJSONAsList(String fileName, Class<T> pojo) {

        try  {
            InputStream jsonFileInStream = JSONUtils.class.getClassLoader().getResourceAsStream("testdata/"+fileName);
            if (jsonFileInStream == null) {
                throw new RuntimeException("Test data file not found: " + fileName);
            }

            JsonNode jsonNode = OBJECT_MAPPER.readTree(jsonFileInStream);
            resolveVariables(jsonNode);

            return OBJECT_MAPPER.convertValue(jsonNode, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, pojo));

        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON test data: " + fileName);
        }
    }

}
