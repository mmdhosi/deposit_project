package controller;

import entity.Transaction;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.TransactionService;

import java.util.List;

@Path("/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionRestController {

    @Inject
    private TransactionService transactionService;

    @POST
    public Response createTransaction(Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return Response.status(Response.Status.CREATED).entity(createdTransaction).build();
    }

    @GET
    @Path("/{id}")
    public Response getTransaction(@PathParam("id") Integer id) {
        Transaction transaction = transactionService.findTransactionById(id);
        if (transaction != null) {
            return Response.ok(transaction).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public List<Transaction> getAllTransactions() {
        return transactionService.findAllTransactions();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTransaction(@PathParam("id") Integer id) {
        if (transactionService.deleteTransaction(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTransaction(@PathParam("id") Integer id, Transaction transaction) {
        transaction.setTransactionId(Math.toIntExact(id)); // Ensure the ID is set for the update
        transactionService.updateTransaction(transaction);
        return Response.ok(transaction).build();
    }
}