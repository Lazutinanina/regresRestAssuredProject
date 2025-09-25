package tests.resources;

import headerService.HeaderService;
import models.resources.ResourcesDto;
import models.resources.ResourcesListResponse;
import org.testng.annotations.Test;
import service.resources.ResourcesService;
import support.LogAll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.testng.AssertJUnit.*;

public class ResourcesTests extends LogAll {
    private final ResourcesService resourcesService = new ResourcesService();

    private List<ResourcesDto> prepairResourcesList(int page) {
        return resourcesService
                .getResourcesResponse(page)
                .statusCode(200)
                .contentType(HeaderService.contentTypeJson)
                .extract()
                .as(ResourcesListResponse.class)
                .getData();
    }

    @Test
    public void basicTest() {
      List<ResourcesDto> resources = prepairResourcesList(1);
        Map<Integer, String> map = new HashMap<>();
        for (ResourcesDto i : resources) {
         map.put(i.getId(), i.getName());
        }
        assertFalse(map.isEmpty());
        assertEquals(map.size(), resources.size());
        Integer anyId = resources.get(0).getId();
        assertTrue(map.containsKey(anyId));
        assertEquals(resources.get(0).getName(), map.get(anyId));
        System.out.println(map);
        System.out.println(resources);
    }

    @Test
    public void sortById(){
        List<ResourcesDto> resources = prepairResourcesList(1);
        TreeMap<Integer, String> sortedMap = new TreeMap<>();
        for (ResourcesDto i : resources) {
            sortedMap.put(i.getId(), i.getName());
        }
        assertFalse(sortedMap.isEmpty());
        Integer iterator = null;
        for (Integer key : sortedMap.keySet()) {
            if (iterator != null)
                assertTrue(iterator < key);
            iterator = key;
        }
        Integer firstId = sortedMap.firstKey();
        Integer lastId = sortedMap.lastKey();
        assertTrue(firstId <= lastId);
        System.out.println(sortedMap);
        System.out.println(firstId);
        System.out.println(lastId);
        System.out.println(sortedMap.keySet());
    }


}


