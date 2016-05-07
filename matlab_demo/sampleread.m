close all; clear all; clc;

fid  = fopen('n1c.log', 'r');
data = textscan(fid, '%s', Inf, 'Delimiter', '\n');
fclose(fid);

data = data{1,1};

good = zeros(8 * size(data,1), 1);

for lc = 1:size(data,1)
    for j = 1:8
        i = (j - 1) * 6 + 12;
        line = data{lc,1};
        lowbyte = line(i:i + 1);
        highbyte = line(i + 3:i + 4);
        good((lc - 1) * 8 + j,1) = hex2dec(lowbyte) + hex2dec(highbyte) * 255; 
    end
end

good = good * 3.3 / hex2dec('FFF');


plot(good)
xlabel('Sample')
ylabel('Voltage (V)')
title('Breadboard Voltage Divider')

axis([0 size(good,1) 0 3.3])
