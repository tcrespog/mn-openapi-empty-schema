package mn.openapi.empty.schema.controller

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse
import mn.openapi.empty.schema.exchange.CreatePetRequest
import mn.openapi.empty.schema.exchange.CreatePetResponse
import mn.openapi.empty.schema.exchange.ErrorResponse

@Controller('/')
@CompileStatic
class PetController {

    @Post("/pet")
    @Operation(
            operationId = 'CreatePet',
            summary = 'Create a pet',
            requestBody = @RequestBody(description = 'Pet create request', content = @Content(schema = @Schema(implementation = CreatePetRequest))),
            responses = [
                    @ApiResponse(responseCode = '200', description = 'OK', content = @Content(schema = @Schema(implementation = CreatePetResponse))),
                    @ApiResponse(responseCode = '400', description = 'Bad request', content = @Content(schema = @Schema(implementation = ErrorResponse))),
                    @ApiResponse(responseCode = '403', description = 'Operation not allowed'),
                    @ApiResponse(responseCode = '409', description = 'Duplicated element', content = @Content(schema = @Schema(implementation = ErrorResponse)))
            ]
    )
    HttpResponse<CreatePetResponse> createPet(@Body CreatePetRequest body) {
        def respBody = new CreatePetResponse(pet: body.pet)

        return HttpResponse.ok(respBody)
    }

}
