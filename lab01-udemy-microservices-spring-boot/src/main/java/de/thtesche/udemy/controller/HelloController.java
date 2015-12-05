/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.thtesche.udemy.controller;

import de.thtesche.udemy.dao.TeamDao;
import de.thtesche.udemy.domain.Team;
import java.net.URI;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author thtesche
 */
@Controller
public class HelloController {

  private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

  @Autowired
  TeamDao teamDao;

  @Autowired
  DiscoveryClient discoveryClient;

  @Value("${content}")
  String content;

  /**
   * Service specific content.
   *
   * @return configured value
   */
  @RequestMapping("/content")
  @ResponseBody
  String content() {
    return content;
  }

  @RequestMapping("/aggregate")
  @ResponseBody
  String aggregate() {

    // Old version. Usefull in case some one don't change the hostname
//    List<String> lab04Instances
//        = discoveryClient.getServices().stream().filter(service -> service.toLowerCase().startsWith("lab-04")).collect(Collectors.toList());
    StringBuilder result = new StringBuilder();
    String delimiter = " - ";

    // Call every registered instance
    List<ServiceInstance> instances = discoveryClient.getInstances("lab-04");
    for (ServiceInstance instance : instances) {
      URI uri = instance.getUri();
      if (uri != null) {
        result.append((new RestTemplate()).getForObject(uri + "/content", String.class));
        result.append(delimiter);
      }
    }
    // remove the last delimiter too
    return result.toString().replaceFirst(delimiter + "$", "");
  }

  @RequestMapping("/hi/{name}")
  public String hiThere(Map model, @PathVariable String name
  ) {
    model.put("name", name);
    return "hello";
  }

  @RequestMapping("/api")
  public @ResponseBody
  List<Team> api() {
    return teamDao.findAll();
  }

}
