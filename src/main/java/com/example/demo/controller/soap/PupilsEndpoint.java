package com.example.demo.controller.soap;

import com.example.demo.form.Contact;
import com.example.demo.form.PupilContact;
import com.example.demo.form.Response;
import com.example.demo.service.ManagingService;
import lt.stipinas.simonas.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class PupilsEndpoint {
    private final static String NAMESPACE_ROOT = "http://simonas.stipinas.lt";

    private final ManagingService managingService;

    public PupilsEndpoint(ManagingService managingService) {
        this.managingService = managingService;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_ROOT, localPart = "insertPupilContactRequest")
    public SimpleMessageResponse insert(@RequestPayload InsertPupilContactRequest request) {
        PupilContact pupilContact = toPupilContact(request.getPupilContact());
        Response response = managingService.insert(pupilContact);
        return simpleResponseFrom(response);
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_ROOT, localPart = "updatePupilContactRequest")
    public SimpleMessageResponse update(@RequestPayload UpdatePupilContactRequest request) {
        Response response = managingService.update(request.getCode(), toPupilContact(request.getUpdatedPupilContact()));
        return simpleResponseFrom(response);
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_ROOT, localPart = "deletePupilContactRequest")
    public SimpleMessageResponse delete(@RequestPayload DeletePupilContactRequest request) {
        Response response = managingService.delete(request.getCode());
        return simpleResponseFrom(response);
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_ROOT, localPart = "getPupilContactRequest")
    public GetPupilContactResponse getPupilContacts(@RequestPayload GetPupilContactRequest request) {
        List<WsPupilContact> pupilContacts;
        if (request.getCode() != null && request.getCode().length() > 0) {
            Response res = managingService.findById(request.getCode());

            pupilContacts = Collections.singletonList(
                    toWsPupilContact((PupilContact) res.getAnswer())
            );
        } else {
            Response res = managingService.fetchClass();

            pupilContacts = ((List<PupilContact>) res.getAnswer())
                    .stream().map(PupilsEndpoint::toWsPupilContact)
                    .collect(Collectors.toList());
        }

        GetPupilContactResponse response = new GetPupilContactResponse();
        response.getContacts().addAll(pupilContacts);
        return response;
    }

    private static SimpleMessageResponse simpleResponseFrom(Response response) {
        SimpleMessageResponse simpleResponse = new SimpleMessageResponse();
        simpleResponse.setMessage(response.getMessage());
        simpleResponse.setStatus(response.getStatus());
        return simpleResponse;
    }

    private static WsPupilContact toWsPupilContact(PupilContact pupilContact) {
        WsContact wsContact = new WsContact();
        wsContact.setId(pupilContact.getContact().getId());
        wsContact.setEmail(pupilContact.getContact().getEmail());
        wsContact.setName(pupilContact.getContact().getName());
        wsContact.setSurname(pupilContact.getContact().getSurname());
        wsContact.setNumber(pupilContact.getContact().getNumber());

        WsPupilContact wsPupilContact = new WsPupilContact();
        wsPupilContact.setName(pupilContact.getName());
        wsPupilContact.setCode(pupilContact.getCode());
        wsPupilContact.setField(pupilContact.getField());
        wsPupilContact.setContact(wsContact);

        return wsPupilContact;
    }

    private static PupilContact toPupilContact(WsPupilContact wsPupilContact) {
        Contact contact = new Contact();
        contact.setId(wsPupilContact.getContact().getId());
        contact.setEmail(wsPupilContact.getContact().getEmail());
        contact.setName(wsPupilContact.getContact().getName());
        contact.setSurname(wsPupilContact.getContact().getSurname());
        contact.setNumber(wsPupilContact.getContact().getNumber());

        PupilContact pupilContact = new PupilContact();
        pupilContact.setName(wsPupilContact.getName());
        pupilContact.setCode(wsPupilContact.getCode());
        pupilContact.setField(wsPupilContact.getField());
        pupilContact.setContact(contact);

        return pupilContact;
    }
}
