package com.tanvir.spring_boot_mvc_jpa_base.movie.adapter.out.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tanvir.spring_boot_mvc_jpa_base.movie.domain.domainentity.MovieInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageDeserializer extends JsonDeserializer<Page<MovieInfo>> {

    @Override
    public Page<MovieInfo> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        ObjectNode root = mapper.readTree(p);

        JsonNode contentNode = root.get("content");
        List<MovieInfo> content = new ArrayList<>();
        if (contentNode.isArray()) {
            for (JsonNode node : contentNode) {
                content.add(mapper.treeToValue(node, MovieInfo.class));
            }
        }

        int page = root.get("number").asInt();
        int size = root.get("size").asInt();
        long totalElements = root.get("totalElements").asLong();

        Pageable pageable = PageRequest.of(page, size);
        return new PageImpl<>(content, pageable, totalElements);
    }
}
