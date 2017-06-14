package com.tayo;

import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;
import com.amazonaws.services.kinesisfirehose.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;


/**
 * Created by temitayo on 6/14/17.
 *  Usage   com.tayo.TrafficViolatorsFirehose TrafficViolators2 us-east-1 /Users/temitayo/Downloads/Traffic_Violations.csv
 */
public class TrafficViolatorsFirehose
{

    public static final String TOKEN = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    public static final String EMPTY = "empty Geo Location";
    public static int counterPlaceholder = 0;


    public static void main(String[] args)
    {

        if(args.length != 3)
        {
            System.out.println("Usage :" + "com.tayo.TrafficViolatorsFirehose <DELIVERY_STREAM_NAME> <AWS_REGION> <FILE_NAME_PATH>");
            System.exit(1);

        }
        String streamName =  args[0];
        String region = args[1];
        String fileNamePath = args[2];
        AmazonKinesisFirehoseClientBuilder builder;
        builder = AmazonKinesisFirehoseClientBuilder.standard().withRegion(region);
        AmazonKinesisFirehose fire = builder.build();
        DescribeDeliveryStreamRequest describeDeliveryStreamRequest = new DescribeDeliveryStreamRequest().withDeliveryStreamName(streamName);
        DescribeDeliveryStreamResult result = fire.describeDeliveryStream(describeDeliveryStreamRequest);
        int count = 0;

        if (result.getDeliveryStreamDescription() != null)
        {
            PutRecordRequest putRecReq = new PutRecordRequest();
            putRecReq.setDeliveryStreamName(streamName);

            try
            {
                FileReader reader = new FileReader(fileNamePath);
                BufferedReader br = new BufferedReader(reader);

                String line = null;

                while((line = br.readLine()) != null)
                {
                    String [] tokens = line.split(TOKEN);
                    System.out.println(tokens.length) ;
                    if(tokens.length == 34)
                        tokens = addSkippedElement(tokens);
                    if(tokens.length > 35)
                        continue;
                    if(tokens.length < 34)
                        continue;
                    ByteBuffer buffer = null;
                    TrafficViolation violation = null;
                    try
                    {
                        violation = new TrafficViolation(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6]
                                ,tokens[7],tokens[8],tokens[9],tokens[10],tokens[11],tokens[12],tokens[13],tokens[14],tokens[15],tokens[16],tokens[17]
                                ,tokens[18],tokens[19],tokens[20],tokens[21],tokens[22],tokens[23],tokens[24],tokens[25],tokens[26],tokens[27]
                                ,tokens[28],tokens[29],tokens[30],tokens[31],tokens[32],tokens[33],tokens[34]);
                        buffer = ByteBuffer.wrap(violation.toString().getBytes("UTF-8"));
                    } catch (UnsupportedEncodingException e)
                    {

                        e.printStackTrace();
                    }
                    Record record = new Record();
                    record.setData(buffer);
                    putRecReq.setRecord(record);
                    PutRecordResult putRecResult = new PutRecordResult();
                    putRecResult = fire.putRecord(putRecReq);
                    System.out.println("Result of putting obj " + violation.toString() + "is record Id " + putRecResult.getRecordId());

                }

            }
            catch (IOException ex)

            {
                ex.printStackTrace();
            }


        }
        System.out.println("Number of records sent to stream successfully is " + count) ;

    }



    public static String [] addSkippedElement(String [] lines)
    {
        String[] result = Arrays.copyOf(lines, lines.length +1);
        result[lines.length] = EMPTY;
        return result;
    }


}
