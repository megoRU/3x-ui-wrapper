package org.threexui;

import org.junit.jupiter.api.Test;
import org.threexui.entity.api.*;
import org.threexui.utils.JsonUtil;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParsingTest {

    @Test
    public void testInboundParsing() {
        String json = "{\"id\": 1, \"remark\": \"test\", \"protocol\": \"vless\", \"port\": 443, \"enable\": true}";
        Inbound inbound = JsonUtil.fromJson(json, Inbound.class);
        assertEquals(1, inbound.getId());
        assertEquals("test", inbound.getRemark());
        assertEquals("vless", inbound.getProtocol());
        assertTrue(inbound.isEnable());
    }

    @Test
    public void testNodeParsing() {
        String json = "{\"id\": 1, \"name\": \"node1\", \"address\": \"1.1.1.1\", \"port\": 2053, \"enable\": true}";
        Node node = JsonUtil.fromJson(json, Node.class);
        assertEquals(1, node.getId());
        assertEquals("node1", node.getName());
        assertEquals("1.1.1.1", node.getAddress());
    }
}
