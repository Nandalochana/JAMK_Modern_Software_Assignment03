/*
 * Swagger Petstore - OpenAPI 3.0
 * This is a sample Pet Store Server based on the OpenAPI 3.0 specification.  You can find out more about Swagger at [https://swagger.io](https://swagger.io). In the third iteration of the pet store, we've switched to the design first approach! You can now help us improve the API whether it's by making changes to the definition itself or to the code. That way, with time, we can improve the API in general, and expose some of the new features in OAS3.  _If you're looking for the Swagger 2.0/OAS 2.0 version of Petstore, then click [here](https://editor.swagger.io/?url=https://petstore.swagger.io/v2/swagger.yaml). Alternatively, you can load via the `Edit > Load Petstore OAS 2.0` menu option!_  Some useful links: - [The Pet Store repository](https://github.com/swagger-api/swagger-petstore) - [The source API definition for the Pet Store](https://github.com/swagger-api/swagger-petstore/blob/master/src/main/resources/openapi.yaml)
 *
 * OpenAPI spec version: 1.0.11
 * Contact: apiteam@swagger.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.api;

import io.swagger.client.model.Category;
import io.swagger.client.model.ModelApiResponse;
import io.swagger.client.model.Pet;
import io.swagger.client.model.Tag;
import org.junit.Test;
import org.junit.Ignore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for PetApi
 */
@Ignore
public class PetApiTest {

    private final PetApi api = new PetApi();

    /**
     * Add a new pet to the store
     *
     * Add a new pet to the store
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void addPetTest() throws Exception {
        Pet body = null;
        Pet response = api.addPet(body);

        // TODO: test validations
    }
    /**
     * Deletes a pet
     *
     * delete a pet
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void deletePetTest() throws Exception {
        Long petId = null;
        String apiKey = null;
        api.deletePet(petId, apiKey);

        // TODO: test validations
    }
    /**
     * Finds Pets by status
     *
     * Multiple status values can be provided with comma separated strings
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void findPetsByStatusTest() throws Exception {
        String status = null;
        List<Pet> response = api.findPetsByStatus(status);

        // TODO: test validations
    }
    /**
     * Finds Pets by tags
     *
     * Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void findPetsByTagsTest() throws Exception {
        List<String> tags = null;
        List<Pet> response = api.findPetsByTags(tags);

        // TODO: test validations
    }
    /**
     * Find pet by ID
     *
     * Returns a single pet
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void getPetByIdTest() throws Exception {
        Long petId = null;
        Pet response = api.getPetById(petId);

        // TODO: test validations
    }
    /**
     * Update an existing pet
     *
     * Update an existing pet by Id
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void updatePetTest() throws Exception {
        Pet body = null;
        Pet response = api.updatePet(body);

        // TODO: test validations
    }
    /**
     * Updates a pet in the store with form data
     *
     * 
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void updatePetWithFormTest() throws Exception {
        Long petId = null;
        String name = null;
        String status = null;
        api.updatePetWithForm(petId, name, status);

        // TODO: test validations
    }
    /**
     * uploads an image
     *
     * 
     *
     * @throws Exception
     *          if the Api call fails
     */
    @Test
    public void uploadFileTest() throws Exception {
        Long petId = null;
        Object body = null;
        String additionalMetadata = null;
        ModelApiResponse response = api.uploadFile(petId, body, additionalMetadata);

        // TODO: test validations
    }
}