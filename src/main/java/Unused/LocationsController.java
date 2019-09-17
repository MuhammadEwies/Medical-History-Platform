//package controllers;
//
//import HibernateSecurity.HibernateCRUDPatient;
//import Services.Locations;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class LocationsController {
//
//
//    @RequestMapping(value = "/Location",method = RequestMethod.POST)
//    boolean addLocation (@RequestParam ("token") String token ,@RequestBody Locations clinicLocation){
//
//        HibernateCRUDPatient hib = new HibernateCRUDPatient();
//        if(hib.checktoken(token)) {
//            int id = hib.getIdbyToken(token);
//            if(id!=0){
//                clinicLocation.setId(id);
//                 return  hib.saveLocation(clinicLocation);
//            }
//        }
//        return false;
//    }
//
//    @RequestMapping(value = "/Location",method = RequestMethod.PUT)
//    boolean updateLocation (@RequestParam ("token") String token ,@RequestBody Locations clinicLocation){
//
//        HibernateCRUDPatient hib = new HibernateCRUDPatient();
//        if(hib.checktoken(token)) {
//            int id = hib.getIdbyToken(token);
//            if(id!=0){
//                clinicLocation.setId(id);
//                return  hib.updateLocation(clinicLocation);
//            }
//        }
//        return false;
//    }
//
//
//    @RequestMapping(value = "/Location",method = RequestMethod.GET)
//    Locations getLocation (@RequestParam ("token") String token){
//
//        HibernateCRUDPatient hib = new HibernateCRUDPatient();
//        if(hib.checktoken(token)) {
//            int id = hib.getIdbyToken(token);
//            System.out.println("id is " +id);
//            if(id!=0){
//                Locations clinicLocation = new Locations() ;
//                 clinicLocation.setId(id);
//                return  hib.getLocationById(id);
//            }
//        }
//        return new Locations();
//    }
//
//
//}
//
