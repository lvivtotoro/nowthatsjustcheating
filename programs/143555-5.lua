function a(b, c)
  if not c then c = 1 end
  if b == 0 then return 0 end
  return b * c * #(tostring(b)) + a(b - 1, c + 1)
end

print(a(tonumber(args[0])))
