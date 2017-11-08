package com.aws;

import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;
import com.amazonaws.services.kinesisfirehose.model.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;



public class LoadJsonData
{
    public static void main(String[] args)
    {

        if(args.length != 3)
        {
            System.out.println("Usage :" + "com.aws.LoadJsonData <DELIVERY_STREAM_NAME> <AWS_REGION> <FILE_NAME_PATH>");
            System.exit(1);

        }
        //check if delivery stream
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
                JSONParser jsonParser = new JSONParser();
                JSONArray jsonArray = (JSONArray)jsonParser.parse(reader);

                String line = null;

                for (int j =0; j < jsonArray.size(); j++)
                {
                    ByteBuffer buffer = null;
                    buffer = ByteBuffer.wrap(jsonArray.get(j).toString().getBytes("UTF-8"));

                    Record record = new Record();
                    record.setData(buffer);
                    putRecReq.setRecord(record);
                    PutRecordResult putRecResult = new PutRecordResult();
                    putRecResult = fire.putRecord(putRecReq);
                    System.out.println("Result of putting obj " + jsonArray.get(j).toString() + "is record Id " + putRecResult.getRecordId());
                    ++count;
                }



            }
            catch (IOException ex)

            {
                ex.printStackTrace();
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }


        }
        System.out.println("Number of records sent to stream successfully is " + count) ;

    }

}
