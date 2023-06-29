package com.springboot.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        return getMappingJacksonValue(new MappingJacksonValue(someBean), "field1");
    }


    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {
        List<SomeBean> someBeans = Arrays.asList(
                new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"),
                new SomeBean("value7", "value8", "value9")
        );
        return getMappingJacksonValue(new MappingJacksonValue(someBeans), "field2");
    }

    private static MappingJacksonValue getMappingJacksonValue(MappingJacksonValue someBean, String field) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(field, "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        someBean.setFilters(filters);
        return someBean;
    }
}
